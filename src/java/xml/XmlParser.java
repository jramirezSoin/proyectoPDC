/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Joseph Ramírez
 */
public class XmlParser {
    
    private static ArrayList<String> lista = new ArrayList<>();
    
    public static void mainR(Node node, int level, String indicador){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) node;
                  if(eElement.hasChildNodes()) {
                    NodeList nl = node.getChildNodes();
                    if(nl.getLength()>1){
                        for(int j=0; j<nl.getLength(); j++) {
                            Node nd = nl.item(j);
                            mainR(nd, level+1, indicador);
                        }}
                  else{
                     Element eElement2 = (Element) nl;
                    if(eElement2.getNodeName().equals("name")){
                        if(node.getParentNode().getNodeName().equals(indicador)){
                            lista.add(eElement2.getTextContent());}
                       }
                  }
                }
    }}
    
    public static void mainRSeleccionado(Node node, int level){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) node;
                  if(eElement.hasChildNodes()) {
                    NodeList nl = node.getChildNodes();
                    if(nl.getLength()>1){
                       lista.add(((Element)nl).getNodeName());
                    for(int j=0; j<nl.getLength(); j++) {
                      Node nd = nl.item(j);
                      mainRSeleccionado(nd, level+1);
                      }}
                  else{
                     Element eElement2 = (Element) nl;                    
                     lista.add(eElement2.getNodeName()+": "+eElement2.getTextContent());
                  }
                    
                    }else{                    
                     lista.add(eElement.getNodeName()+": "+eElement.getTextContent());
                  }
                }
    }
    
    public static Document mainR2(Node node, int level, Element element, Document doc){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) node;
                  if(eElement.hasChildNodes()) {
                    NodeList nl = node.getChildNodes();
                    if(nl.getLength()>1){
                       
                       Element element2 = doc.createElement(((Element)nl).getNodeName());
                       NamedNodeMap  namedNodeMap = eElement.getAttributes();
                       
                       for(int i =namedNodeMap.getLength()-1; i>=0;i--){ 
                        Attr attr;
                        attr = doc.createAttribute(namedNodeMap.item(i).getNodeName());
                        attr.setValue(namedNodeMap.item(i).getTextContent());
                        element2.setAttributeNode(attr);
                        
                            
                       }
                       element.appendChild(element2);
                       
                    for(int j=0; j<nl.getLength(); j++) {
                      Node nd = nl.item(j);
                      doc = mainR2(nd, level+1, element2 , doc);
                      }
                  }else{
                     Element eElement2 = (Element) nl;
                     Element element2 = doc.createElement(eElement2.getNodeName());
                     element2.appendChild(doc.createTextNode(eElement2.getTextContent()));
                     element.appendChild(element2);
                  }
                    
                    }else{
                     Element element2 = doc.createElement(eElement.getNodeName());
                     element2.appendChild(doc.createTextNode(eElement.getTextContent()));
                     element.appendChild(element2);
                  }
                }
        return doc;
    }
    
    public static ArrayList<String> Leer(File file, String indicador){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getChildNodes().item(0).getChildNodes();
            lista.clear();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                mainR(node, 0, indicador);                
            }
            return lista;
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static ArrayList<String> LeerSeleccionado(File file, int index){
        try {
            lista.clear();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getChildNodes().item(0).getChildNodes();
            Node node = nList.item(index);
            mainRSeleccionado(node, 0);
            return lista;
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static void Modificar(String archivoViejo, String archivoNuevo, String contenido, String tag, int id){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc1 = db.parse(new ByteArrayInputStream(contenido.getBytes("UTF-8")));
            Node element = doc1.getElementsByTagName(tag).item(0);
            // 1. cargar el XML original
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivoViejo));
            
            // 2. buscar y eliminar el elemento <enfermera id="3"> de entre
            //    muchos elementos <enfermera> ubicados en cualquier posicion del documento
            NodeList items = doc.getElementsByTagName(tag);
            element = doc.importNode(element, true);
            items.item(id).getParentNode().replaceChild(element, items.item(id));
            
            
            // 3. Exportar nuevamente el XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(archivoNuevo));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
        } catch (SAXException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void Eliminar(String archivoViejo, String archivoNuevo, String tag, int id){
        try {
            // 1. cargar el XML original
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivoViejo));
            
            // 2. buscar y eliminar el elemento <enfermera id="3"> de entre
            //    muchos elementos <enfermera> ubicados en cualquier posicion del documento
            NodeList items = doc.getElementsByTagName(tag);
            items.item(id).getParentNode().removeChild(items.item(id));
            // 3. Exportar nuevamente el XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(archivoNuevo));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
        } catch (SAXException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void Agregar(String archivoViejo, String archivoNuevo, String contenido, String tag){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc1 = db.parse(new ByteArrayInputStream(contenido.getBytes("UTF-8")));
            Node element = doc1.getElementsByTagName(tag).item(0);
            // 1. cargar el XML original
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivoViejo));
            
            // 2. buscar y eliminar el elemento <enfermera id="3"> de entre
            //    muchos elementos <enfermera> ubicados en cualquier posicion del documento
            NodeList items = doc.getElementsByTagName(tag);
            element = doc.importNode(element, true);
            items.item(0).getParentNode().appendChild(element);
            
            
            // 3. Exportar nuevamente el XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(archivoNuevo));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
        } catch (SAXException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
