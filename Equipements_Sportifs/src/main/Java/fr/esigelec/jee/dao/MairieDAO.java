package fr.esigelec.jee.dao;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.File;
import java.sql.SQLException;
import java.sql.Types;

public class MairieDAO extends DBDAO
{
    public static void main(String[] argv)
    {
        File folder = new File("cleanDatas");
        File[] listOfFiles = folder.listFiles();
        String codeInsee = "";

        dbConnect();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    String[] strings = file.getName().split("\\.");
                    codeInsee = strings[0];
                }
                try
                {
                    DocumentBuilderFactory docBuild = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = docBuild.newDocumentBuilder();
                    Document doc = documentBuilder.parse(file);
                    doc.getDocumentElement().normalize();
                    System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                    NodeList nodeList = doc.getElementsByTagName("Organisme");

                    for (int itr = 0; itr < nodeList.getLength(); itr++) {
                        Node node = nodeList.item(itr);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            // Mairies
//                                preparedStatement = connection.prepareStatement("INSERT INTO `mairie` (mairie_id, mairie_nom) VALUES (?, ?)");
//                                preparedStatement.setString(1, codeInsee);
//                                preparedStatement.setString(2, element.getElementsByTagName("Nom").item(0).getTextContent());
//                                System.out.println(preparedStatement.toString());
//                                /*if(preparedStatement.executeUpdate() != 1)
//                                    // TODO : print error
//                                    System.out.println("error");*/
//
//                                // Adresses
//                                preparedStatement = connection.prepareStatement("INSERT INTO `mairie_adresse` " +
//                                        "(mairie_adresse.adresse_id, " +
//                                        "mairie_adresse.adresse_ligne, " +
//                                        "mairie_adresse.adresse_codePostal, " +
//                                        "mairie_adresse.adresse_commune, " +
//                                        "mairie_adresse.adresse_latitude, " +
//                                        "mairie_adresse.adresse_longitude, " +
//                                        "mairie_adresse.adresse_precision) " +
//                                        "VALUES (?, ?, ?, ?, ?, ?, ?)");
//                                preparedStatement.setString(1, codeInsee);
//                                preparedStatement.setString(2, element.getElementsByTagName("Ligne").item(0).getTextContent());
//                                preparedStatement.setString(3, element.getElementsByTagName("CodePostal").item(0).getTextContent());
//                                preparedStatement.setString(4, element.getElementsByTagName("NomCommune").item(0).getTextContent());
//                                preparedStatement.setString(5, element.getElementsByTagName("Latitude").item(0).getTextContent());
//                                preparedStatement.setString(6, element.getElementsByTagName("Longitude").item(0).getTextContent());
//                                preparedStatement.setString(7, element.getElementsByTagName("Précision").item(0).getTextContent());
//                                System.out.println(preparedStatement.toString());
//                                /*if(preparedStatement.executeUpdate() != 1)
//                                    // TODO : print error
//                                    System.out.println("error");*/
//
//                                // Coordonnées
//                                preparedStatement = connection.prepareStatement("INSERT INTO `mairie_coordonnees` " +
//                                        "(mairie_coordonnees.coordonnees_id, " +
//                                        "mairie_coordonnees.coordonnees_telephone," +
//                                        "mairie_coordonnees.coordonnees_mail," +
//                                        "mairie_coordonnees.coordonnees_url) " +
//                                        "VALUES (?, ?, ?, ?)");
//                                preparedStatement.setString(1, codeInsee);
//                                preparedStatement.setString(2, element.getElementsByTagName("Téléphone").item(0).getTextContent());
//                                preparedStatement.setString(3, element.getElementsByTagName("Email").item(0).getTextContent());
//                                if(element.getElementsByTagName("Url").item(0).getTextContent() == null)
//                                    preparedStatement.setString(4, element.getElementsByTagName("Url").item(0).getTextContent());
//                                else
//                                    preparedStatement.setNull(4, Types.VARCHAR);
//                                System.out.println(preparedStatement.toString());
//                                /*if(preparedStatement.executeUpdate() != 1)
//                                    // TODO : print error
//                                    System.out.println("error");*/
//
//                                // Ouverture
//                                preparedStatement = connection.prepareStatement("INSERT INTO `mairie_ouverture` " +
//                                        "(mairie_ouverture.ouverture_id, " +
//                                        "mairie_ouverture.ouverture_plageJ_debut," +
//                                        "mairie_ouverture.ouverture_plageJ_fin," +
//                                        "mairie_ouverture.ouverture_plageH_debut," +
//                                        "mairie_ouverture.ouverture_plageH_fin) " +
//                                        "VALUES (?, ?, ?, ?, ?)");
//                                preparedStatement.setString(1, codeInsee);
//                                preparedStatement.setString(2, element.getAttribute("début"));

                            DocumentBuilderFactory docBuildOuverture = DocumentBuilderFactory.newInstance();
                            DocumentBuilder documentBuilderOuverture = docBuild.newDocumentBuilder();
                            Document docOuverture = documentBuilder.parse(file);
                            docOuverture.getDocumentElement().normalize();
                            NodeList nodeListOuverture = doc.getElementsByTagName("Ouverture");

                            for(int iter = 0; iter < nodeList.getLength(); iter++) {
                                Node nodeOuverture = nodeList.item(itr);
                                if(nodeOuverture.getNodeType() == Node.ELEMENT_NODE) {
                                    Element elementOuverture = (Element) nodeOuverture;
                                    try {
                                        if(element.getElementsByTagName("PlageJ").item(0).getTextContent() != null)
                                            preparedStatement.setString(3, element.getElementsByTagName("PlageJ").item(0).getTextContent());
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            System.out.println(preparedStatement.toString());
                                /*if(preparedStatement.executeUpdate() != 1)
                                    // TODO : print error
                                    System.out.println("error");*/

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        dbClose();
    }
}  