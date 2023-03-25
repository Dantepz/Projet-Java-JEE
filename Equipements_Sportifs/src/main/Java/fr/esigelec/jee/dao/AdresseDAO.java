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
public Adresse getAdresseofMairie(String insee) {
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
}

    //public Adresse

    public ArrayList<Adresse> getAdressByInsee(){

    }




}
