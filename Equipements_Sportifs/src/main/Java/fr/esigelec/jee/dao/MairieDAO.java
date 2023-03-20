package fr.esigelec.jee.dao;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) node;
                            try {
                                // Mairies
//                                preparedStatement = connection.prepareStatement("INSERT INTO `mairie` (mairie_id, mairie_nom) VALUES (?, ?)");
//                                preparedStatement.setString(1, codeInsee);
//                                preparedStatement.setString(2, eElement.getElementsByTagName("Nom").item(0).getTextContent());
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
//                                preparedStatement.setString(2, eElement.getElementsByTagName("Ligne").item(0).getTextContent());
//                                preparedStatement.setString(3, eElement.getElementsByTagName("CodePostal").item(0).getTextContent());
//                                preparedStatement.setString(4, eElement.getElementsByTagName("NomCommune").item(0).getTextContent());
//                                preparedStatement.setString(5, eElement.getElementsByTagName("Latitude").item(0).getTextContent());
//                                preparedStatement.setString(6, eElement.getElementsByTagName("Longitude").item(0).getTextContent());
//                                preparedStatement.setString(7, eElement.getElementsByTagName("Précision").item(0).getTextContent());
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
//                                preparedStatement.setString(2, eElement.getElementsByTagName("Téléphone").item(0).getTextContent());
//                                preparedStatement.setString(3, eElement.getElementsByTagName("Email").item(0).getTextContent());
//                                if(eElement.getElementsByTagName("Url").item(0).getTextContent() == null)
//                                    preparedStatement.setString(4, eElement.getElementsByTagName("Url").item(0).getTextContent());
//                                else
//                                    preparedStatement.setNull(4, Types.VARCHAR);
//                                System.out.println(preparedStatement.toString());
//                                /*if(preparedStatement.executeUpdate() != 1)
//                                    // TODO : print error
//                                    System.out.println("error");*/

                                // Ouverture
                                preparedStatement = connection.prepareStatement("INSERT INTO `mairie_ouverture` " +
                                        "(mairie_ouverture.ouverture_id, " +
                                        "mairie_ouverture.ouverture_plageJ_debut," +
                                        "mairie_ouverture.ouverture_plageJ_fin," +
                                        "mairie_ouverture.ouverture_plageH_debut," +
                                        "mairie_ouverture.ouverture_plageH_fin) " +
                                        "VALUES (?, ?, ?, ?, ?)");
                                preparedStatement.setString(1, codeInsee);
                                NamedNodeMap namedNodeMap = eElement.getElementsByTagName("Téléphone").item(0).getAttributes();
                                /*preparedStatement.setString(2, eElement.getAttribute("début"));
                                preparedStatement.setString(3, eElement.getElementsByTagName("Email").item(0).getTextContent());
                                if(eElement.getElementsByTagName("Url").item(0).getTextContent() == null)
                                    preparedStatement.setString(4, eElement.getElementsByTagName("Url").item(0).getTextContent());
                                else
                                    preparedStatement.setString(4, "");*/
                                System.out.println(preparedStatement.toString());
                                /*if(preparedStatement.executeUpdate() != 1)
                                    // TODO : print error
                                    System.out.println("error");*/

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
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