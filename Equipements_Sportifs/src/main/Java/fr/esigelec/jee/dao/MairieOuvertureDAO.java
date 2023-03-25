package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MairieOuvertureDAO extends DAO{

    public MairieOuvertureDAO(){
        super();
    }



    public ArrayList<MairieOuverture> getMOZipCode(String zipcode){
        dbconnect();
        ArrayList<MairieOuverture> mairOuvs = null;

        try{
            String query = "SELECT m_o.mairie_insee, m_o.ouverture " +
                    "FROM m_o INNER JOIN mairie_adresse " +
                    "ON m_o.mairie_insee = mairie_adresse.mairie_insee " +
                    "WHERE mairie_adresse.adresse_codePostal = ? OR mairie_adresse.adresse_codePostal LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,zipcode);
            pstmt.setString(2,zipcode.substring(0,2)+"%");
            ResultSet rset = pstmt.executeQuery();
            mairOuvs = new ArrayList<>();
            while(rset.next()){
               mairOuvs.add(new MairieOuverture(rset.getString(1), rset.getInt(2) ));

            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }
        return mairOuvs;
    }

}
