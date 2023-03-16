package fr.esigelec.jee.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class MairieDAO
{
    public static void main(String[] argv)
    {
        File folder = new File("cleanDatas");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
                try
                {
                    //an instance of factory that gives a document builder
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    //an instance of builder to parse the specified xml file
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(file);
                    doc.getDocumentElement().normalize();
                    System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                    NodeList nodeList = doc.getElementsByTagName("Organisme");
                    // nodeList is not iterable, so we are using for loop
                    for (int itr = 0; itr < nodeList.getLength(); itr++) {
                        Node node = nodeList.item(itr);
                        System.out.println("\nNode Name :" + node.getNodeName());
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) node;
                            System.out.println("Nom: "+ eElement.getElementsByTagName("Nom").item(0).getTextContent());
                            System.out.println("Adresse: "+ eElement.getElementsByTagName("Adresse").item(0).getTextContent());
                            System.out.println("Coordonnées Numériques: "+ eElement.getElementsByTagName("CoordonnéesNum").item(0).getTextContent());
                            System.out.println("Horaires d'ouverture: "+ eElement.getElementsByTagName("Ouverture").item(0).getTextContent());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}  