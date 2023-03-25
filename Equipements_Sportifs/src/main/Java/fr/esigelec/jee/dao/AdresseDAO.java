package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.Adresse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdresseDAO extends DAO{
    public AdresseDAO(){
        super();
    }

    /*public ArrayList<Adresse> getAllAddresses(){
        dbconnect();
        ArrayList<Adresse> adresses =null;
        try{
            String query = "SELECT * FROM mairie_adresse WHERE 1";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            adresses = new ArrayList<>();

            while(rs.next()){
                adresses.add(new Adresse(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }
        return adresses;
    }*/

    /**
     * Recuperer l'adresse par commune
     */
/*public Adresse getAdresseofMairie(String insee) {
    dbconnect();
    Adresse adresse = null;
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    try {
        String query = "SELECT * FROM mairie_adresse WHERE 1";
         pstmt = con.prepareStatement(query);
         rset = pstmt.executeQuery();
    }catch(SQLException se){
        se.printStackTrace();
    }finally{
        dbclose(rset);
        dbclose(pstmt);
        dbclose();
    }
    return adresse;
}*/

    //public Adresse

    /**
     *
     * @param zipcode
     * @return une liste d'adresses par code zip;
     */
    public ArrayList<Adresse> getAdressByZipCode(String zipcode){
        dbconnect();
        ArrayList<Adresse> adresses =null;
        try{
            String query = "SELECT mairie_adresse.adresse_id, mairie_adresse.adresse_ligne, mairie_adresse.adresse_codePostal, " +
                    " mairie_adresse.adresse_commune, mairie_adresse.adresse_latitude, mairie_adresse.adresse_longitude, mairie_adresse.adresse_precision," +
                    " mairie_adresse.mairie_insee" +
                    " FROM mairie_adresse  WHERE mairie_adresse.adresse_codePostal = ? OR mairie_adresse.adresse_codePostal LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, zipcode);
            pstmt.setString(2, zipcode.substring(0,2)+"%");
            ResultSet rs = pstmt.executeQuery();
            adresses = new ArrayList<>();

            while(rs.next()){
                adresses.add(new Adresse(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7),rs.getString(8)));
            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }
        return adresses;
    }

    public static void main (String []  args){

        AdresseDAO adao = new AdresseDAO();
        ArrayList<Adresse> adr = adao.getAdressByZipCode("31100");
        System.out.println(adr);
        System.out.println(adr.size());
    }

}
