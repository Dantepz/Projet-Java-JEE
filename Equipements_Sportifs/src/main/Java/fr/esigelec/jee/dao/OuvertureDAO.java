package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.Ouverture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OuvertureDAO extends DAO{

    public OuvertureDAO(){
        super();
    }

    public ArrayList<Ouverture> getSpecificOvertures(String insee){
        dbconnect();

        try{
            String query = "SELECT * FROM ouverture W";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,insee);

            ResultSet rset = pstmt.executeQuery();

            if(rset.next()){

            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }
    }
}
