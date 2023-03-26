package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.Coordonnee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoordonneeDAO extends DAO{

    public CoordonneeDAO(){
        super();
    }
    /**
     * retrieving data by  zipcode
     */
    public ArrayList<Coordonnee> getCoordsByZipCode(String zipcode){
        dbconnect();
        ArrayList<Coordonnee>  coordonnees = null;
        try{
            String query = "SELECT mairie_coordonnee.coordonnees_id, mairie_coordonnee.coordonnees_telephone," +
                    "mairie_coordonnee.coordonnees_mail, mairie_coordonnee.coordonnees_url, mairie_coordonnee.mairie_insee, mairie_adresse.adresse_codePostal " +
                    " FROM mairie_coordonnee INNER JOIN mairie_adresse" +
                    " ON mairie_coordonnee.mairie_insee = mairie_adresse.mairie_insee" +
                    " WHERE mairie_adresse.adresse_codePostal = ? OR mairie_adresse.adresse_codePostal LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,zipcode);
            pstmt.setString(2,zipcode.substring(0,2)+"%");
            ResultSet rset = pstmt.executeQuery();
            coordonnees = new ArrayList<>();
            while(rset.next()){
                coordonnees.add(new Coordonnee(rset.getInt(1),rset.getString(2),rset.getString(3), rset.getString(4),rset.getString(5)));
            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }
        return coordonnees;
    }

    public static void main (String [] args){
        CoordonneeDAO coordao = new CoordonneeDAO();
        ArrayList<Coordonnee> coor = coordao.getCoordsByZipCode("31100");
        System.out.println(coor);
    }
}
