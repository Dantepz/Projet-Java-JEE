package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.Mairie;
import fr.esigelec.jee.models.Ouverture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class OuvertureDAO extends DAO{

    public OuvertureDAO(){
        super();
    }


    public ArrayList<Ouverture> getSpecificOvertures(String insee){
        dbconnect();
        ArrayList<Ouverture> ouvertures = null;
        try{
            String query = "SELECT mairie_ouverture.ouverture_id, mairie_ouverture.ouverture_plageJ_debut, mairie_ouverture.ouverture_plageJ_fin, mairie_ouverture.ouverture_plageH_debut, mairie_ouverture.ouverture_plageH_fin FROM mairie_ouverture, m_o WHERE mairie_ouverture.ouverture_id = m_o.ouverture AND m_o.mairie_insee = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,insee);

            ResultSet rset = pstmt.executeQuery();
            ouvertures = new ArrayList<>();
            while(rset.next()){
                //System.out.println(rset.getTimestamp("ouverture_plageH_fin").toString());

                ouvertures.add(new Ouverture( rset.getInt(1), rset.getString(2), rset.getString(3), rset.getTimestamp(4), rset.getTimestamp(5)));


            }

        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }
        return ouvertures;
    }

    public ArrayList<Ouverture> getDeptOuvertures(String codePostal){
        dbconnect();
        ArrayList<Ouverture> ouvertures = null;
        ArrayList<Integer> idOuvertures = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String depcode = codePostal.substring(0,2)+"%" ;
        try{
            String query = "SELECT mairie_ouverture.ouverture_id, mairie_ouverture.ouverture_plageJ_debut, mairie_ouverture.ouverture_plageJ_fin, mairie_ouverture.ouverture_plageH_debut, mairie_ouverture.ouverture_plageH_fin, m_o.mairie_insee, mairie_adresse.adresse_codePostal " +
                    "FROM m_o INNER JOIN mairie_adresse " +
                    "ON m_o.mairie_insee = mairie_adresse.mairie_insee " +
                    "INNER JOIN mairie_ouverture " +
                    "ON m_o.ouverture = mairie_ouverture.ouverture_id " +
                    "WHERE mairie_adresse.adresse_codePostal = ? OR mairie_adresse.adresse_codePostal LIKE ?";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,codePostal);
            pstmt.setString(2,depcode);
            rset = pstmt.executeQuery();
            ouvertures = new ArrayList<>();
            idOuvertures = new ArrayList<>();

            while(rset.next()){
                //System.out.println(rset.getTimestamp("ouverture_plageH_fin").toString());

                Ouverture ouv = new Ouverture(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getTimestamp(4), rset.getTimestamp(5));
                int idOuverture = rset.getInt(1);

                int index = idOuvertures.indexOf(idOuverture);
                if(index == -1){
                    ouv.addMairieRelated(new Mairie(rset.getString(6)));
                    idOuvertures.add(idOuverture);
                    ouvertures.add(ouv);
                }else{
                    Ouverture ouvLocal = ouvertures.get(index);
                    ouvLocal.addMairieRelated(new Mairie(rset.getString(6)));
                    ouvertures.set(index, ouvLocal);
                    System.out.println(rset.getString(7));
                }
            }

        }catch(SQLException se){
            se.printStackTrace();
        } finally{
            dbclose(rset);
            dbclose(pstmt);
            dbclose();
        }
        return ouvertures;
    }

    public static void main (String [] args){
        long start = System.currentTimeMillis();
        OuvertureDAO odao = new OuvertureDAO();
        ArrayList<Ouverture> ouvs = odao.getDeptOuvertures("31100");
        //Collections.sort(ouvs);
        System.out.println(ouvs);
        System.out.println(ouvs.size());
        System.out.println("Run time " + (System.currentTimeMillis() - start));
    }
}
