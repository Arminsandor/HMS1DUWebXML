package hu.domparse.HMS1DU;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DOMReadHMS1DU {
    public static void main(String[] args) {
        try {
            // XML file betöltés
            File inputFile = new File("XMLHMS1DU.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            
            // vendégek
            System.out.println("\nVendégek:");
            NodeList vendegList = doc.getElementsByTagName("vendeg");
            for (int i = 0; i < vendegList.getLength(); i++) {
                Node node = vendegList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Vendég ID: " + element.getElementsByTagName("vendegID").item(0).getTextContent());
                    System.out.println("Név: " + element.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Nem: " + element.getElementsByTagName("nem").item(0).getTextContent());
                }
            }
            
            // szobák 
            System.out.println("\nSzobák:");
            NodeList szobaList = doc.getElementsByTagName("szoba");
            for (int i = 0; i < szobaList.getLength(); i++) {
                Node node = szobaList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Szobaszám: " + element.getElementsByTagName("szobaszam").item(0).getTextContent());
                    System.out.println("Típus: " + element.getElementsByTagName("tipus").item(0).getTextContent());
                    System.out.println("Ár: " + element.getElementsByTagName("ar").item(0).getTextContent());
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}