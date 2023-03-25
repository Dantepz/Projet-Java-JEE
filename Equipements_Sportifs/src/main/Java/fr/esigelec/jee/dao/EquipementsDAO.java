package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.Equipements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipementsDAO extends DAO{

    public EquipementsDAO(){
        super();
    }
    public static final int EQUIPEMENTS_MAX_PAR_REQUETE = 300;

    /**
     * Recuperer une liste d'équiment à partir d'un code postal
     */
    public ArrayList<Equipements> getEquipByZipCode(String zipcode, int occurrence){
        dbconnect();
        ArrayList<Equipements> equipements = null;
        int startLine = occurrence*EQUIPEMENTS_MAX_PAR_REQUETE;
        int counter = 0;
        String depcode = zipcode.substring(0,2)+"%" ;
        try {
            String query = "SELECT *" +
                    " FROM equipement_sportif" +
                    " INNER JOIN mairie_adresse" +
                    " ON equipement_sportif.com_insee = mairie_adresse.mairie_insee" +
                    " WHERE mairie_adresse.adresse_codePostal = ? OR mairie_adresse.adresse_codePostal LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,zipcode);
            pstmt.setString(2,depcode);
            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                if (counter >= startLine && counter < (EQUIPEMENTS_MAX_PAR_REQUETE + startLine)) {
                    System.out.println(rset.getString(1) + " " + counter);
                }

                counter++;
            }
        }catch (SQLException se){
            se.printStackTrace();
        }

        return equipements;
    }
    public ArrayList<Equipements> getEquipByZipCode(String zipcode){
        return getEquipByZipCode(zipcode,0);
    }

    public ArrayList<Equipements> getEquipByCoord(float x, float y, float radius, int occurrence){
        dbconnect();
        ArrayList<Equipements> equipements = null;
        int startLine = occurrence*EQUIPEMENTS_MAX_PAR_REQUETE;
        int counter = 0;
        try {
            String query = "SELECT *" +
                    " FROM equipement_sportif WHERE equip_gpsx IS NOT NULL AND equip_gpsx IS NOT NULL AND ABS(equip_gpsx - ?) < ? AND ABS(equip_gpsx - ?) < ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setFloat(1,x);
            pstmt.setFloat(2,radius/2);
            pstmt.setFloat(3,y);
            pstmt.setFloat(4,radius/2);
            ResultSet rset = pstmt.executeQuery();
            while(rset.next()) {
                if(counter >= startLine && counter < (EQUIPEMENTS_MAX_PAR_REQUETE+startLine)) {
                    System.out.println(rset.getString(1)+" "+counter);
                }
                counter++;
            }
        }catch (SQLException se){
            se.printStackTrace();
        }

        return equipements;
    }

    public ArrayList<Equipements> getEquipByCoord(float x, float y, float radius){
        return getEquipByCoord(x,y,radius,0);
    }
    public static void main (String [] args){
        EquipementsDAO equipdao = new EquipementsDAO();
        equipdao.getEquipByZipCode("31100");
        //equipdao.getEquipByCoord(4.7f,4.7f,1000.000f);
    }

}
