/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import control.ControlPath;
import datos.ListaT;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
    
    public static void mainRSeleccionado(Node node, int level,ArrayList<String> lista){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) node;
                  if(eElement.hasChildNodes()) {
                    NodeList nl = node.getChildNodes();
                    if(nl.getLength()>1){
                       lista.add(((Element)nl).getNodeName());
                    for(int j=0; j<nl.getLength(); j++) {
                      Node nd = nl.item(j);
                      mainRSeleccionado(nd, level+1, lista);
                      }}
                  else{
                     Element eElement2 = (Element) nl;
                     lista.add(convSpecialChar(eElement2.getNodeName()+": "+eElement2.getTextContent()));
                  }
                    
                    }else{
                     lista.add(convSpecialChar(eElement.getNodeName()+": "+eElement.getTextContent()));
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
    
    public static ArrayList<String> Leer2(File file, String indicador){
        return Leer2(file,indicador,"name",false);
    }
    
    public static ArrayList<String> Leer2(File file, String indicador,String valor){
        return Leer2(file,indicador,valor,false);
    }
    
    public static ArrayList<String> Leer2(File file, String indicador, String valor, boolean conjunto){
        try {
            ArrayList<String> lista = new ArrayList<String>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            lista.clear();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                String k= convSpecialChar(((Element)node).getElementsByTagName(valor).item(0).getTextContent());
                if((!conjunto) || (conjunto && !lista.contains(k)))
                    lista.add(k);
            }
            return (ArrayList<String>)lista.clone();
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static ArrayList<String> LeerSeleccionado(File file,String indicador, int index){
        try {
            ArrayList<String> lista = new ArrayList<String>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            Node node = nList.item(index);
            mainRSeleccionado(node, 0,lista);
            if(node.hasAttributes() && node.getAttributes().getNamedItem("iceUpdater")!=null){
                lista.add(1,"iceUpdater: "+node.getAttributes().getNamedItem("iceUpdater").getNodeValue());
            }
            return (ArrayList<String>)lista.clone();
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static void Modificar(String archivoViejo, String archivoNuevo, String contenido,int updater, String tag, int id){
        try {
            contenido = contenido.replaceAll("[ \t]+<.+></.+>\n", "");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc1 = db.parse(new ByteArrayInputStream(contenido.getBytes("UTF-8")));
            Node element = doc1.getElementsByTagName(tag).item(0);
            updater++;
            ((Element)element).setAttribute("iceUpdater", updater+"");
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
            contenido = contenido.replaceAll("[ \t]+<.+></.+>\n", "");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc1 = db.parse(new ByteArrayInputStream(contenido.getBytes("UTF-8")));
            Node element = doc1.getElementsByTagName(tag).item(0);
            ((Element)element).setAttribute("iceUpdater", "new");
            // 1. cargar el XML original
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            try{
                builder.parse(new File(archivoViejo));}
            catch(FileNotFoundException e){
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(archivoViejo));
                bw.write(getPlantilla(archivoViejo));
                bw.close();
            }
            Document doc = builder.parse(new File(archivoViejo));
            NodeList items = doc.getElementsByTagName(tag);
            element = doc.importNode(element, true);
            try{
            items.item(0).getParentNode().appendChild(element);
            }catch(NullPointerException e){
            doc.getFirstChild().appendChild(element);
            }
            
            
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

    public static void EliminarMasivo(String archivoViejo, String archivoNuevo, String tag, ArrayList<Integer> index) {
         
            try {
            // 1. cargar el XML original
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(archivoViejo));
            // 2. buscar y eliminar el elemento <enfermera id="3"> de entre
            //    muchos elementos <enfermera> ubicados en cualquier posicion del documento
            NodeList items = doc.getElementsByTagName(tag);
            Node parent= items.item(0).getParentNode();
            int cont=0;
            for(int i: index){
                   parent.removeChild(items.item(i-cont));
                   cont++;
            }
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
    
    public static ArrayList<String> Buscar(File file, String indicador, ArrayList<ListaT> buscar){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            ArrayList<String> lista = new ArrayList<String>();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                int cont=0;
                int ap=0;
                for(int j=0; j< node.getChildNodes().getLength(); j++){
                    for(int k=0; k< buscar.size();k++){
                    if(node.getChildNodes().item(j).getNodeName().equals(buscar.get(k).unit)){
                        ap++;
                        if(!node.getChildNodes().item(j).getTextContent().equals(buscar.get(k).valor)){
                            cont++;
                            k=buscar.size();
                            j= node.getChildNodes().getLength();
                        }
                    }
                    }
                }
                if(ap!=buscar.size()) cont++;
                if(cont==0) lista.add(convSpecialChar(convSpecialChar(((Element)node).getElementsByTagName("name").item(0).getTextContent())));
            }
            return (ArrayList<String>)lista.clone();
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static ArrayList<String> Buscar(File file, String indicador, ArrayList<ListaT> buscar, ListaT child){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            ArrayList<String> lista = new ArrayList<String>();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                int cont=0;
                int ap=0;
                for(int j=0; j< node.getChildNodes().getLength(); j++){
                    for(int k=0; k< buscar.size();k++){
                    if(node.getChildNodes().item(j).getNodeName().equals(buscar.get(k).unit)){
                        ap++;
                        if(!node.getChildNodes().item(j).getTextContent().equals(buscar.get(k).valor)){
                            cont++;
                            k=buscar.size();
                            j= node.getChildNodes().getLength();
                        }
                    }
                    }
                }
                if(ap!=buscar.size()) cont++;
                if(cont==0){
                    String[] arrOfStr = child.unit.split(",");
                    NodeList nChilds=((Element)node).getElementsByTagName(child.unit);
                    for (String a : arrOfStr){
                        nChilds = ((Element)node).getElementsByTagName(a);
                        node= nChilds.item(0);
                    }
                    for(int j=0; j< nChilds.getLength(); j++){
                        lista.add(convSpecialChar(((Element)nChilds.item(j)).getElementsByTagName(child.valor).item(0).getTextContent()));
                    }
                }
            }
            return (ArrayList<String>)lista.clone();
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static ArrayList<String> BuscarDeep(File file, String indicador, ArrayList<ListaT> buscar, String child, Boolean conjunto){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            ArrayList<String> lista = new ArrayList<String>();
            ListaT prueba= new ListaT();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if(buscar.size()!=0){
                for(int j=0; j< buscar.size(); j++){
                 ListaT bus= buscar.get(j);
                 if(!bus.valor.equals("")){
                    if(!((Element)node).getElementsByTagName(bus.unit).item(0).getTextContent().equals(bus.valor)){
                      break;  
                    }else{
                        prueba= bus;
                        buscar.remove(j);
                        i=-1;
                        j--;
                    }
                   }else{
                    nList= ((Element)node).getElementsByTagName(bus.unit);
                    i=-1;
                    buscar.remove(j);
                    prueba= new ListaT();
                    break;
                 }
                }}else{
                    if(prueba.unit.equals("") || ((Element)node).getElementsByTagName(prueba.unit).item(0).getTextContent().equals(prueba.valor)){
                        String t = convSpecialChar(((Element)node).getElementsByTagName(child).item(0).getTextContent());
                        if((!conjunto) || (conjunto && !lista.contains(t)))
                            lista.add(t);
                    }
                }
            }
            return (ArrayList<String>)lista.clone();
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static String BuscarUno(File file, String indicador, ListaT buscar, String retorna){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if(((Element)node).getElementsByTagName(buscar.unit).item(0).getTextContent().equals(buscar.valor)){
                    if(retorna.equals("id")) return i+"";
                    return convSpecialChar(((Element)node).getElementsByTagName(retorna).item(0).getTextContent());
                }
            }
            return "null";
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static ArrayList<ListaT> LeerBalance(File file, String indicador){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            ArrayList<ListaT> balances= new ArrayList<>();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                balances.add(new ListaT(convSpecialChar(((Element)node).getElementsByTagName("numericCode").item(0).getTextContent()),convSpecialChar(((Element)node).getElementsByTagName("name").item(0).getTextContent())));
            }
            return balances;
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static ArrayList<ListaT> LeerEvent(File file,String indicador,String evento){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(indicador);
            ArrayList<ListaT> balances= new ArrayList<>();
            String path="";
            for (int i = 0; i < nList.getLength(); i++) {
                Element node = (Element)nList.item(i);
                if(node.getElementsByTagName("name").item(0).getTextContent().equals(evento)) path=node.getElementsByTagName("className").item(0).getTextContent();
            }
            return balances;
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static String convSpecialChar(String xmlString)
    {	
        
        xmlString=xmlString.replaceAll("&", "&amp;");
        xmlString=xmlString.replaceAll("\'", "&apo;");
        xmlString=xmlString.replaceAll("\"", "&quot;");
        xmlString=xmlString.replaceAll("<", "&lt;");
        xmlString=xmlString.replaceAll(">", "&gt;");
        return xmlString;
    }
    
    public static ArrayList<ListaT> LeerConstante(String indicador){
        File file= new File(ControlPath.constants);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("constant");
            ArrayList<ListaT> constants = new ArrayList<>();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if(((Element)node).getElementsByTagName("name").item(0).getTextContent().equals(indicador)){
                    NodeList nList2 = ((Element)node).getElementsByTagName("value");
                    for (int j = 0; j < nList2.getLength(); j++) {
                        Node node2 = nList2.item(j);
                        constants.add(new ListaT(((Element)node2).getAttribute("value"),((Element)node2).getTextContent()));
                    }
                }
            }
            return constants;
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            return null;
        }
    }
    
    public static int getUpdates(String path, String tipo, String user){
        try {
            File file = new File(ControlPath.path+user+"/"+path+"_"+tipo+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            Document doc2 = db.newDocument();
            doc2.setXmlStandalone(true);
            String inicial, namespace;
            namespace="http://xmlns.oracle.com/communications/platform/model/pricing";
            inicial="pdc:PricingObjectsJXB"; 
            if(tipo.equals("config")){
            namespace="http://xmlns.oracle.com/communications/platform/model/Config";
            inicial="cim:ConfigObjects";
            }else if(tipo.equals("metadata")){
            namespace="http://xmlns.oracle.com/communications/platform/model/Metadata";    
            inicial="mtd:MetadataObjects"; 
            }
            Element ePdc = doc2.createElementNS(namespace,inicial);
            doc2.appendChild(ePdc);
            int cont=0;
            NodeList nList = doc.getChildNodes().item(0).getChildNodes();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if(node.hasAttributes() && node.getAttributes().getNamedItem("iceUpdater")!=null){
                    Node node2 = doc2.importNode(node, true);
                    NamedNodeMap attributes = node2.getAttributes();
                    attributes.removeNamedItem("iceUpdater");
                    attributes = node.getAttributes();
                    attributes.removeNamedItem("iceUpdater");
                    ePdc.appendChild(node2);
                    cont++;
                }
            }
            if(cont!=0){
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
            DOMSource source = new DOMSource(doc2);
            StreamResult result = new StreamResult(new File(ControlPath.path+user+"/"+path+"_"+tipo+"2.xml"));
            transformer.transform(source, result);}
            return cont;
            
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static void deleteIceUpdater(String path, String tipo, String user){
        try {
            File file = new File(ControlPath.path+user+"/"+path+"_"+tipo+".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            Document doc2 = db.newDocument();
            doc2.setXmlStandalone(true);
            String inicial, namespace;
            namespace="http://xmlns.oracle.com/communications/platform/model/pricing";
            inicial="pdc:PricingObjectsJXB"; 
            if(tipo.equals("config")){
            namespace="http://xmlns.oracle.com/communications/platform/model/Config";
            inicial="cim:ConfigObjects";
            }else if(tipo.equals("metadata")){
            namespace="http://xmlns.oracle.com/communications/platform/model/Metadata";    
            inicial="mtd:MetadataObjects"; 
            }
            Element ePdc = doc2.createElementNS(namespace,inicial);
            doc2.appendChild(ePdc);
            NodeList nList = doc.getChildNodes().item(0).getChildNodes();
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                Node node2 = doc2.importNode(node, true);
                if(node2.hasAttributes() && node2.getAttributes().getNamedItem("iceUpdater")!=null){
                    NamedNodeMap attributes = node2.getAttributes();
                    attributes.removeNamedItem("iceUpdater");
                }
                ePdc.appendChild(node2);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
            DOMSource source = new DOMSource(doc2);
            StreamResult result = new StreamResult(new File(ControlPath.path+user+"/"+path+"_"+tipo+".xml"));
            transformer.transform(source, result);
            
        } catch(IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getPlantilla(String archivoViejo) {
        if(archivoViejo.matches("(.*)_pricing.xml"))
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><pdc:PricingObjectsJXB xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\"></pdc:PricingObjectsJXB>";
        else if(archivoViejo.matches("(.*)_config.xml"))
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><cim:ConfigObjects xmlns:cim=\"http://xmlns.oracle.com/communications/platform/model/Config\"></cim:ConfigObjects>";
        else if(archivoViejo.matches("(.*)_metadata.xml"))
            return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><mtd:MetadataObjects xmlns:mtd=\"http://xmlns.oracle.com/communications/platform/model/Metadata\"></mtd:MetadataObjects>";
        else
            return "";
    }

    public static int agregarExports(String file, String tipo, String usuario) {
        String archivo = ControlPath.path+usuario+"/"+file+"_"+tipo+".xml";
        String archivo2 = ControlPath.path+usuario+"/"+file+"2_"+tipo+".xml";
        int cont = 0;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder db = dbf.newDocumentBuilder();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document doc = builder.parse(new File(archivo));
            Document doc2 = builder.parse(new File(archivo2));
            //doc.getChildNodes().item(0).getChildNodes();
            NodeList items = doc.getChildNodes().item(0).getChildNodes();
            NodeList items2 = doc2.getChildNodes().item(0).getChildNodes();
            boolean estado=false;
            for(int i=0; i<items2.getLength(); i++){
                Element ni = (Element)items2.item(i);
                estado=false;
                String name= ni.getElementsByTagName("name").item(0).getTextContent();
                for(int j=0; j<items.getLength(); j++){
                    Element nj = (Element)items.item(j);
                    if(nj.getElementsByTagName("name").item(0).getTextContent().equals(name)){
                        if(nj.hasAttribute("iceUpdater")){
                            cont+= nj.getAttribute("iceUpdater").equals("new")?0:Integer.parseInt(nj.getAttribute("iceUpdater"));
                        }
                        ni = (Element)doc.importNode(ni, true);
                        items.item(j).getParentNode().replaceChild(ni, nj);
                        estado= true;
                        break;
                    }
                }
                if(!estado){
                    ni = (Element)doc.importNode(ni, true);
                    items.item(0).getParentNode().appendChild(ni);
                }
                estado=false;
            }
            
            // 3. Exportar nuevamente el XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(archivo));
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
        return cont;
    }
    
}
