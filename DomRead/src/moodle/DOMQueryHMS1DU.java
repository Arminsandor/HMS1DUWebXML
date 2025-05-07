import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryHMS1DU {
   public static void main(String[] args) throws ParserConfigurationException;
   {
    File xmlFile = new File("XY_XML.xml");

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dbuilder = factory.newDocumentBuilder();

    Document doc = dbuilder.parse(xmlFile);
    
    doc.getDocumentElement().normalize();
    
    System.out.println("Gyökér elem: " + doc.getDocumentElement() + "/n");
    System.out.pritnln("Azok a szakácsok, akinek a végzettségeik között van szakközépiskola:/n");
    
    NodeList nodList = doc.getElementsByTagName("szakcs");
    
    for (int i = 0; i < nodeList.getLength(); i ++ ) {
    	Node nNode = nodeList.Item(i);
    	
    	if(nNode.getNodeType() == Node.ELEMENT_NODE) 
    }
    
    
    
    
    
    
    
    Document doc = dBuilder.parse(xmlFile);
    
    doc.getDocumentElement().normalize();
    
    NodeList nList = doc.getElementsByTagName("vendeg");
    
    for (int i = 0; i < nList.getLength(); i++ ) {
    	Node vendeg = doc.getElementsByTagName("vendeg").item(i);
    	
    	NodeList list = vendeg.getChildNodes();
    	
    for ( int temp = 0; temp < list.getLength(); temp++ ) {
    	Node node = list.item(temp);
    	if (node.getNodeType() == Node.ELEMENT_NODE) {
    		Element eElement = (Element) node;
    	}
    }
    }