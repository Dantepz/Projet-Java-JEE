package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.EquipementType;
import fr.esigelec.jee.models.Equipement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipementDAO extends DAO{

    public EquipementDAO(){
        super();
    }

    private EquipementTypeDAO eqtypdao = null;
    private ArrayList<EquipementType> equityps = null;


    public static final int EQUIPEMENTS_MAX_PAR_REQUETE = 300;

    /**
     * Recuperer une liste d'équiment à partir d'un code postal
     */
    public ArrayList<Equipement> getEquipByZipCode(String zipcode, int occurrence, int max){
        dbconnect();
        eqtypdao = new EquipementTypeDAO();
        equityps = eqtypdao.getEquipTypeByZipCode(zipcode);
        ArrayList<Equipement> equipements = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int startLine = occurrence*max;
        String depcode = zipcode.substring(0,2)+"%" ;
        try {
            String query = "SELECT DISTINCT equipement_sportif.equipment_id, equipement_sportif.equ_nom, equipement_sportif.com_insee, equipement_sportif.ins_numero_install, " +
                    " equipement_sportif.ins_nom, equipement_sportif.nature_libelle, equipement_sportif.equ_surface_evolution, equipement_sportif.equip_gpsx, " +
                    " equipement_sportif.equip_gpsy, equipement_sportif.equipment_type_code_id" +
                    " FROM equipement_sportif" +
                    " INNER JOIN mairie_adresse" +
                    " ON equipement_sportif.com_insee = mairie_adresse.mairie_insee" +
                    " WHERE mairie_adresse.adresse_codePostal = ? OR mairie_adresse.adresse_codePostal LIKE ? LIMIT ? OFFSET ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,zipcode);
            pstmt.setString(2,depcode);
            pstmt.setInt(3,max);
            pstmt.setInt(4,startLine);
            rset = pstmt.executeQuery();
            equipements = new ArrayList<>();
            while(rset.next()) {
               Equipement equip = new Equipement(rset.getString(1),rset.getString(2),
               rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6),
                       rset.getFloat(7),rset.getFloat(8),rset.getFloat(9),null);

                String type = rset.getString(10);
               for(EquipementType eq : equityps){
                   if(eq.getEquipTypeCode().equals(type)){
                       equip.setEquipTypeCodeId(eq);
                       break;
                   }
               }
                equipements.add(equip);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            dbclose(rset);
            dbclose(pstmt);
            dbclose();
        }
        return equipements;
    }
    public ArrayList<Equipement> getEquipByZipCode(String zipcode){
        return getEquipByZipCode(zipcode,0,EQUIPEMENTS_MAX_PAR_REQUETE);
    }

    public ArrayList<Equipement> getEquipByCoord(float x, float y, float radius, int occurrence){
        dbconnect();
        ArrayList<Equipement> equipements = null;
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
            equipements = new ArrayList<>();
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

    public ArrayList<Equipement> getEquipByCoord(float x, float y, float radius){
        return getEquipByCoord(x,y,radius,0);
    }
    public static void main (String [] args){
        long start = System.currentTimeMillis();
        EquipementDAO equipdao = new EquipementDAO();
        ArrayList<Equipement>  list = equipdao.getEquipByZipCode("31100", 1, 1000);
        System.out.println(list);
        System.out.println(list.size());
        //equipdao.getEquipByCoord(4.7f,4.7f,1000.000f);
        System.out.println("Run time : "+(System.currentTimeMillis()-start));
    }

}
