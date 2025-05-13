package hu.domparse.HMS1DU;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DOMModifyHMS1DU {
    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLHMS1DU.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            // szoba státusz frissités
            String szobaszam = "101";
            NodeList szobaList = doc.getElementsByTagName("szoba");
            for (int i = 0; i < szobaList.getLength(); i++) {
                Node node = szobaList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getElementsByTagName("szobaszam").item(0).getTextContent().equals(szobaszam)) {
                        element.getElementsByTagName("status").item(0).setTextContent("foglalt");
                        System.out.println("Szoba " + szobaszam + " státusza módosítva 'foglalt'-ra");
                    }
                }
            }
            
            // új vendég hozzáadása
            Element newVendeg = doc.createElement("vendeg");
            
            Element vendegID = doc.createElement("vendegID");
            vendegID.appendChild(doc.createTextNode("V003"));
            newVendeg.appendChild(vendegID);
            
            Element nev = doc.createElement("nev");
            nev.appendChild(doc.createTextNode("Kiss Anna"));
            newVendeg.appendChild(nev);
            
            
            
            doc.getDocumentElement().appendChild(newVendeg);
            System.out.println("Új vendég hozzáadva");
            
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLHMS1DU.xml"));
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}