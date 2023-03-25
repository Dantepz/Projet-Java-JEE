package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.Adresse;
import fr.esigelec.jee.models.Coordonnee;
import fr.esigelec.jee.models.Mairie;
import fr.esigelec.jee.models.Ouverture;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class MairieDAO extends DAO
{
    public MairieDAO() {
        super();
    }

    /**
     * DAOs and initialization
     */
    private CoordonneeDAO coordDao;
    private AdresseDAO addrDao;
    private OuvertureDAO ouvDao;
    private void initDAOs(){
        coordDao = new CoordonneeDAO();
        addrDao = new AdresseDAO();
        ouvDao = new OuvertureDAO();
    }

    /**
     * Data feeding.
     */
    private ArrayList<Coordonnee> coords;
    private ArrayList<Adresse> addrs;
    private ArrayList<Ouverture> ouvs;
    public void feedList(String zipcode){
        coords = coordDao.getCoordsByZipCode(zipcode);
        addrs = addrDao.getAdressByZipCode(zipcode);
        ouvs = ouvDao.getDeptOuvertures(zipcode);
    }

    public void emptyingdata() {
        coords.removeAll(coords);
        addrs.removeAll(addrs);
        ouvs.removeAll(ouvs);
    }



   /* public static void main(String[] argv)
    {
        MairieDAO mdao = new MairieDAO();

        File folder = new File("cleanDatas");
        File[] listOfFiles = folder.listFiles();
        String codeInsee = "";

        mdao.dbconnect();

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
                    PreparedStatement preparedStatement;

                    for (int itr = 0; itr < nodeList.getLength(); itr++) {
                        Node node = nodeList.item(itr);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            // Mairies
                            /*preparedStatement = mdao.con.prepareStatement("INSERT INTO `mairie` (mairie_insee, mairie_nom) VALUES (?, ?)");
                            preparedStatement.setString(1, codeInsee);
                            preparedStatement.setString(2, element.getElementsByTagName("Nom").item(0).getTextContent());
                            System.out.println(preparedStatement.toString());
                            if(preparedStatement.executeUpdate() != 1)
                                // TODO : print error
                                System.out.println("error");
                            */
                            // Adresses
                            /*preparedStatement = mdao.con.prepareStatement("INSERT INTO `mairie_adresse` " +
                                    "(mairie_adresse.adresse_ligne, " +
                                    "mairie_adresse.adresse_codePostal, " +
                                    "mairie_adresse.adresse_commune, " +
                                    "mairie_adresse.adresse_latitude, " +
                                    "mairie_adresse.adresse_longitude, " +
                                    "mairie_adresse.adresse_precision," +
                                    "mairie_adresse.mairie_insee) " +
                                    "VALUES (?, ?, ?, ?, ?, ?,?)");
                            if(element.getElementsByTagName("Ligne").item(0) != null)
                                preparedStatement.setString(1, element.getElementsByTagName("Ligne").item(0).getTextContent());
                            else
                                preparedStatement.setNull(1, Types.VARCHAR);

                            if(element.getElementsByTagName("CodePostal").item(0) != null)
                                preparedStatement.setString(2, element.getElementsByTagName("CodePostal").item(0).getTextContent());
                            else
                                preparedStatement.setNull(2, Types.VARCHAR);

                            if(element.getElementsByTagName("NomCommune").item(0) != null)
                                preparedStatement.setString(3, element.getElementsByTagName("NomCommune").item(0).getTextContent());
                            else
                                preparedStatement.setNull(3, Types.VARCHAR);

                            if(element.getElementsByTagName("Latitude").item(0) != null)
                                preparedStatement.setString(4, element.getElementsByTagName("Latitude").item(0).getTextContent());
                            else
                                preparedStatement.setNull(4, Types.VARCHAR);

                            if(element.getElementsByTagName("Longitude").item(0) != null)
                                preparedStatement.setString(5, element.getElementsByTagName("Longitude").item(0).getTextContent());
                            else
                                preparedStatement.setNull(5, Types.VARCHAR);

                            if(element.getElementsByTagName("Précision").item(0) != null)
                                preparedStatement.setString(6, element.getElementsByTagName("Précision").item(0).getTextContent());
                            else
                                preparedStatement.setNull(6, Types.VARCHAR);


                            preparedStatement.setString(7,codeInsee);
                            System.out.println(preparedStatement.toString());
                            if(preparedStatement.executeUpdate() != 1)
                                // TODO : print error
                               System.out.println("error");

                            // Coordonnées

                            preparedStatement = mdao.con.prepareStatement("INSERT INTO `mairie_coordonnee` " +
                                    "(mairie_coordonnee.coordonnees_telephone," +
                                    "mairie_coordonnee.coordonnees_mail," +
                                    "mairie_coordonnee.coordonnees_url, " +
                                    "mairie_coordonnee.mairie_insee)"+
                                    "VALUES (?, ?, ?, ?)");
                            if(element.getElementsByTagName("Téléphone").item(0) != null)
                            preparedStatement.setString(1, element.getElementsByTagName("Téléphone").item(0).getTextContent());
                            else
                                preparedStatement.setNull(1, Types.VARCHAR);
                            if(element.getElementsByTagName("Email").item(0) != null)
                                preparedStatement.setString(2, element.getElementsByTagName("Email").item(0).getTextContent());
                            else{
                                preparedStatement.setNull(2, Types.VARCHAR);
                            }
                            if(element.getElementsByTagName("Url").item(0) != null)
                                preparedStatement.setString(3, element.getElementsByTagName("Url").item(0).getTextContent());
                            else
                                preparedStatement.setNull(3, Types.VARCHAR);
                            System.out.println(preparedStatement.toString());
                            preparedStatement.setString(4,codeInsee);
                            if(preparedStatement.executeUpdate() != 1)
                                // TODO : print error
                                System.out.println("error");

                            // Ouverture
                           /*preparedStatement = mdao.con.prepareStatement("INSERT INTO `mairie_ouverture` " +
                                    "(mairie_ouverture.ouverture_id, " +
                                    "mairie_ouverture.ouverture_plageJ_debut," +
                                    "mairie_ouverture.ouverture_plageJ_fin," +
                                    "mairie_ouverture.ouverture_plageH_debut," +
                                    "mairie_ouverture.ouverture_plageH_fin) " +
                                    "VALUES (?, ?, ?, ?, ?)");
                            preparedStatement.setString(1, codeInsee);

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
                                        if(element.getElementsByTagName("PlageJ").item(0).getTextContent() != null) {
                                            preparedStatement.setString(2, element.getAttribute("début"));
                                            preparedStatement.setString(3, element.getElementsByTagName("PlageJ").item(0).getTextContent());
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }*/
                            //System.out.println(preparedStatement.toString());
                            /*if(preparedStatement.executeUpdate() != 1)
                                // TODO : print error
                                System.out.println("error");*/
                        /*}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        mdao.dbclose();
    }*/

    public ArrayList<Mairie> getMairiesByZipCode(String zipcode){
        dbconnect();
        ArrayList<Mairie> mairiesBasics = null;
        initDAOs();
        feedList(zipcode);

        try{
            String query = "SELECT mairie.mairie_insee, mairie.mairie_nom FROM mairie INNER JOIN mairie_adresse" +
                    " ON mairie.mairie_insee = mairie_adresse.mairie_insee" +
                    " WHERE mairie_adresse.adresse_codePostal = ? OR mairie_adresse.adresse_codePostal LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,zipcode);
            pstmt.setString(2,zipcode.substring(0,2)+"%");
            ResultSet rset = pstmt.executeQuery();
            mairiesBasics = new ArrayList<>();
            int [] indexes = new int[3];

            while(rset.next()) {
                Mairie mairie = new Mairie(rset.getString(1));
                Mairie mairieClone = new Mairie(rset.getString(1));

                mairie.setNom(rset.getString(2));

                for(int i = 0; i < ouvs.size(); i++){
                        if(ouvs.get(i).getMairiesRelated().contains(mairieClone)){
                            mairie.addOuverture(ouvs.get(i));
                        }
                        System.out.println(ouvs.get(i).getMairiesRelated());
                }

                for(int i = 0; i < coords.size();i++){
                    if(coords.get(i).getInsee().equals(rset.getString(1))){
                        mairie.setCoordonnees(coords.get(i));
                        break;
                    }
                }
                for(int i = 0; i < addrs.size() ; i++){
                    if(addrs.get(i).getInsee().equals(rset.getString(1))){
                        mairie.setAdresse(addrs.get(i));
                        break;}

                }

                mairiesBasics.add(mairie);
            }
        }catch(SQLException se) {
            se.printStackTrace();
        }finally{
            dbclose();
        }

        return mairiesBasics;
    }

    public static void main (String [] args){
        long start = System.currentTimeMillis();
        MairieDAO mdao = new MairieDAO();
        ArrayList<Mairie> mairies = mdao.getMairiesByZipCode("31100");
        System.out.println(mairies);
        System.out.println("Run time =" + (System.currentTimeMillis() - start) );
    }
}  