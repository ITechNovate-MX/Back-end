package com.ITechNovate.asr_backend.service;

import com.ITechNovate.asr_backend.dto.FacturaDTO;
import com.ITechNovate.asr_backend.models_sql.Factura;
import com.ITechNovate.asr_backend.models_sql.Material;
import com.ITechNovate.asr_backend.repository.FacturaRepository;
import com.ITechNovate.asr_backend.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private MaterialRepository materialRepository;

    // Método para subir una factura desde un archivo XML
    public FacturaDTO saveFacturaFromXml(MultipartFile file) {
        Factura factura = new Factura();
        try {
            System.out.println("Procesando archivo: " + file.getOriginalFilename());

            // Crear DocumentBuilder para leer el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = file.getInputStream();
            Document document = builder.parse(inputStream);

// Extraer los valores desde el XML
            NodeList comprobanteList = document.getElementsByTagName("cfdi:Comprobante");
            if (comprobanteList.getLength() > 0) {
                Element comprobanteElement = (Element) comprobanteList.item(0);

                // Obtener folio
                String folioStr = comprobanteElement.getAttribute("Folio");
                if (!folioStr.isEmpty()) {
                    factura.setFolio(Integer.parseInt(folioStr));
                } else {
                    throw new RuntimeException("Folio no encontrado en el archivo XML");
                }

                // Obtener fecha de emisión
                String fechaStr = comprobanteElement.getAttribute("Fecha");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                // Asegurar que la fecha tenga zona horaria explícita
                dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC")); // Configurar UTC
                Date fechaEmision = dateFormat.parse(fechaStr);
                factura.setFechaEmision(fechaEmision);

                // Obtener subtotal y total
                factura.setSubtotal(new BigDecimal(comprobanteElement.getAttribute("SubTotal")));
                factura.setTotal(new BigDecimal(comprobanteElement.getAttribute("Total")));

                // Obtener método de pago
                String metodoPago = comprobanteElement.getAttribute("MetodoPago");
                factura.setMetodoPago(metodoPago != null && !metodoPago.isEmpty() ? metodoPago : "No especificado");

                // Obtener moneda
                String moneda = comprobanteElement.getAttribute("Moneda");
                factura.setMoneda(moneda != null && !moneda.isEmpty() ? moneda : "N/A");
            }


            // Extraer datos del receptor (cliente)
            NodeList receptorList = document.getElementsByTagName("cfdi:Receptor");
            if (receptorList.getLength() > 0) {
                Element receptorElement = (Element) receptorList.item(0);
                String cliente = receptorElement.getAttribute("Nombre");
                factura.setCliente(cliente);
            }

            // Extraer la orden de compra desde la descripción
            NodeList conceptoList = document.getElementsByTagName("cfdi:Concepto");
            if (conceptoList.getLength() > 0) {
                Element conceptoElement = (Element) conceptoList.item(0);

                String descripcion = conceptoElement.getAttribute("Descripcion");
                if (descripcion != null && !descripcion.isEmpty()) {
                    // Reemplazar caracteres no deseados y normalizar
                    descripcion = descripcion.replaceAll("&#xA;", "\n").replaceAll("[\\n\\t\\r]+", " ").trim();
                    System.out.println("Descripción después de limpieza: " + descripcion);

                    String ordenCompra = null;

                    // Buscar "ORDEN DE COMPRA" o "OC" seguido de un valor
                    Pattern pattern = Pattern.compile("(ORDEN DE COMPRA|OC|O.C.)\\s*([a-zA-Z0-9-/]+)");
                    Matcher matcher = pattern.matcher(descripcion);

                    if (matcher.find()) {
                        ordenCompra = matcher.group(2).trim(); // Extraer el valor después de "ORDEN DE COMPRA" o "OC"
                    } else {
                        // Si no se encontró un prefijo válido, buscar después de "\n"
                        int indexA = descripcion.indexOf("\n");
                        if (indexA != -1) {
                            // Extraer texto después del primer salto de línea
                            String afterA = descripcion.substring(indexA + 1).trim();
                            String[] palabras = afterA.split(" ");
                            if (palabras.length > 0) {
                                ordenCompra = palabras[0].trim(); // Tomar la primera palabra después de "\n"
                            }
                        }
                    }

                    // Limitar longitud a 100 caracteres (o lo que permita tu columna)
                    if (ordenCompra != null && !ordenCompra.isEmpty()) {
                        int maxLength = 100; // Cambiar según la longitud permitida en la base de datos
                        if (ordenCompra.length() > maxLength) {
                            ordenCompra = ordenCompra.substring(0, maxLength).trim();
                        }
                        factura.setOrdenCompra(ordenCompra);
                        System.out.println("Orden de compra detectada: " + ordenCompra);
                    } else {
                        factura.setOrdenCompra("No especificado");
                        System.out.println("No se encontró un formato válido para la orden de compra.");
                    }
                } else {
                    factura.setOrdenCompra("No especificado");
                }

            }

            // Guardar factura solo si no existe
            Optional<Factura> existingFactura = facturaRepository.findById(factura.getFolio());
            if (existingFactura.isPresent()) {
                factura = existingFactura.get();
                System.out.println("Factura ya existe. Usando la existente con ID: " + factura.getFolio());
            } else {
                factura.setArchivoXml(file.getOriginalFilename());
                factura = facturaRepository.save(factura);
                System.out.println("Factura nueva guardada con ID: " + factura.getFolio());
            }

            // Guardar los materiales asociados solo si no existen
            for (int i = 0; i < conceptoList.getLength(); i++) {
                Element conceptoElement = (Element) conceptoList.item(i);

                Material material = new Material();

                // Obtener la descripción completa
                String descripcion = conceptoElement.getAttribute("Descripcion");

                // Buscar la palabra "CATALOGO" y extraer el número siguiente
                String noParte = "0"; // Valor por defecto si no se encuentra
                if (!descripcion.isEmpty()) {
                    int index = descripcion.indexOf("CATALOGO");
                    if (index != -1) {
                        // Extraer el texto después de "CATALOGO"
                        String catalogoPart = descripcion.substring(index + 8).trim();

                        // Obtener el primer número encontrado
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d+");
                        java.util.regex.Matcher matcher = pattern.matcher(catalogoPart);
                        if (matcher.find()) {
                            noParte = matcher.group(); // Extraer el número encontrado
                        }
                    }
                }
                material.setNoParte(noParte);
                material.setDescripcion(descripcion);
                material.setCantidad(new BigDecimal(conceptoElement.getAttribute("Cantidad")));
                material.setPrecioUnitario(new BigDecimal(conceptoElement.getAttribute("ValorUnitario")));
                material.setImporte(new BigDecimal(conceptoElement.getAttribute("Importe")));

                // Verificar IVA dentro del nodo de impuestos o concepto
                NodeList impuestosList = conceptoElement.getElementsByTagName("cfdi:Traslado");
                if (impuestosList.getLength() > 0) {
                    Element impuestoElement = (Element) impuestosList.item(0);
                    material.setIva(new BigDecimal(impuestoElement.getAttribute("Importe")));
                }

                // Verificar si el material ya existe para esta factura
                Optional<Material> existingMaterial = materialRepository.findByNoParteAndFactura(noParte, factura);
                if (existingMaterial.isEmpty()) {
                    material.setFactura(factura);
                    materialRepository.save(material);
                    System.out.println("Nuevo material guardado: " + noParte);
                } else {
                    System.out.println("Material ya existe para esta factura: " + noParte);
                }
            }

            // Convertir a DTO y retornar
            return new FacturaDTO(factura.getFolio(), factura.getFechaEmision(), factura.getCliente(), factura.getOrdenCompra(),
                    factura.getSubtotal(), factura.getTotal(), factura.getMetodoPago(), factura.getArchivoXml(), factura.getMoneda());

        } catch (Exception e) {
            System.err.println("Error al procesar el archivo XML: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al procesar el archivo XML", e);
        }
    }




    // Método para actualizar los datos de una factura
    public FacturaDTO updateFactura(Integer id, FacturaDTO facturaDTO) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        factura.setCliente(facturaDTO.getCliente());
        factura.setTotal(facturaDTO.getTotal());
        factura.setOrdenCompra(facturaDTO.getOrdenCompra());
        factura.setMetodoPago(facturaDTO.getMetodoPago());

        Factura updatedFactura = facturaRepository.save(factura);
        return new FacturaDTO(
                updatedFactura.getFolio(),
                updatedFactura.getFechaEmision(),
                updatedFactura.getCliente(),
                updatedFactura.getOrdenCompra(),
                updatedFactura.getSubtotal(),
                updatedFactura.getTotal(),
                updatedFactura.getMetodoPago(),
                updatedFactura.getArchivoXml(),
                updatedFactura.getMoneda()
        );
    }

    // Método para obtener todas las facturas
    public List<FacturaDTO> getAllFacturas() {
        return facturaRepository.findAll().stream()
                .map(factura -> new FacturaDTO(
                        factura.getFolio(),
                        factura.getFechaEmision(),
                        factura.getCliente(),
                        factura.getOrdenCompra(),
                        factura.getSubtotal(),
                        factura.getTotal(),
                        factura.getMetodoPago(),
                        factura.getArchivoXml(),
                        factura.getMoneda()
                ))
                .collect(Collectors.toList());
    }

    // Método para obtener una factura específica por ID
    public FacturaDTO getFacturaById(Integer id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        return new FacturaDTO(
                factura.getFolio(),
                factura.getFechaEmision(),
                factura.getCliente(),
                factura.getOrdenCompra(),
                factura.getSubtotal(),
                factura.getTotal(),
                factura.getMetodoPago(),
                factura.getArchivoXml(),
                factura.getMoneda()
        );
    }
}
