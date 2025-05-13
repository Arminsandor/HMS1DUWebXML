package hu.domparse.HMS1DU;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

public class DOMQueryHMS1DU {
    
    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLHMS1DU.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            // Bejelentkezési rendszer
            Scanner scanner = new Scanner(System.in);
            System.out.println("=== Hotel Rendszer Bejelentkezés ===");
            System.out.print("Felhasználónév:");
            String username = scanner.nextLine();
            System.out.print("Jelszó:");
            String password = scanner.nextLine();
            
            if (checkLogin(doc, username, password)) {
                System.out.println("\nSikeres bejelentkezés!");
                showMainMenu(doc, scanner);
            } else {
                System.out.println("\nHibás felhasználónév vagy jelszó!");
            }
            
            scanner.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Bejelentkezés ellenőrzése
    public static boolean checkLogin(Document doc, String username, String password) {
        try {
            NodeList szemelyzetList = doc.getElementsByTagName("szemelyzet");
            
            for (int i = 0; i < szemelyzetList.getLength(); i++) {
                Node node = szemelyzetList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Element login = (Element) element.getElementsByTagName("login").item(0);
                    
                    String storedUser = login.getElementsByTagName("felhasznalonev").item(0).getTextContent();
                    String storedPass = login.getElementsByTagName("jelszo").item(0).getTextContent();
                    
                    if (storedUser.equals(username) && storedPass.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Főmenü megjelenítése
    public static void showMainMenu(Document doc, Scanner scanner) {
        while (true) {
            System.out.println("\n=== Főmenü ===");
            System.out.println("1. Szabad szobák listázása");
            System.out.println("2. Vendég foglalásainak lekérdezése");
            System.out.println("3. Kilépés");
            System.out.print("Válassz egy opciót: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Enter lenyomás kezelése
            
            switch (choice) {
                case 1:
                    listAvailableRooms(doc);
                    break;
                case 2:
                    System.out.print("Add meg a vendég ID-t: ");
                    String vendegID = scanner.nextLine();
                    listGuestBookings(doc, vendegID);
                    break;
                case 3:
                    System.out.println("Kilépés...");
                    return;
                default:
                    System.out.println("Érvénytelen választás!");
            }
        }
    }
    
    // Szabad szobák listázása
    public static void listAvailableRooms(Document doc) {
        System.out.println("\nSzabad szobák:");
        NodeList szobaList = doc.getElementsByTagName("szoba");
        for (int i = 0; i < szobaList.getLength(); i++) {
            Node node = szobaList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String status = element.getElementsByTagName("status").item(0).getTextContent();
                if (status.equals("szabad")) {
                    System.out.println("Szobaszám: " + element.getElementsByTagName("szobaszam").item(0).getTextContent());
                    System.out.println("Típus: " + element.getElementsByTagName("tipus").item(0).getTextContent());
                    System.out.println("Ár: " + element.getElementsByTagName("ar").item(0).getTextContent());
                    System.out.println("-------------------");
                }
            }
        }
    }
    
    // Vendég foglalásainak listázása
    public static void listGuestBookings(Document doc, String vendegID) {
        System.out.println("\nFoglalások vendég ID alapján (" + vendegID + "):");
        NodeList foglalasList = doc.getElementsByTagName("foglalas");
        boolean found = false;
        
        for (int i = 0; i < foglalasList.getLength(); i++) {
            Node node = foglalasList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String currentVendegID = element.getElementsByTagName("vendegID").item(0).getTextContent();
                if (currentVendegID.equals(vendegID)) {
                    found = true;
                    System.out.println("Foglalás ID: " + element.getElementsByTagName("foglalasID").item(0).getTextContent());
                    System.out.println("Szobaszám: " + element.getElementsByTagName("szobaszam").item(0).getTextContent());
                    System.out.println("Érkezés: " + element.getElementsByTagName("erkezes").item(0).getTextContent());
                    System.out.println("Távozás: " + element.getElementsByTagName("tavozas").item(0).getTextContent());
                    System.out.println("-------------------");
                }
            }
        }
        
        if (!found) {
            System.out.println("Nincsenek foglalások ehhez a vendég ID-hoz.");
        }
    }
}