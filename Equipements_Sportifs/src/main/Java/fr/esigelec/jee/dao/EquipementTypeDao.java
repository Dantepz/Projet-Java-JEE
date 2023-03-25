package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.Coordonnee;
import fr.esigelec.jee.models.EquipementType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class EquipementTypeDao extends DAO{

    private ArrayList<String> equipmentTypesFamille;
    public EquipementTypeDao(){
        super();
    }

    /**
     * Retreiving Types Equipments from database.
     * @return
     */
    public ArrayList<EquipementType> getEquipementTypes(){
        /**
         * Declaring a list of models EquipmentType.
         */
        ArrayList<EquipementType> equipementTypes = null;

        /**
         * connecting to database.
         */
        dbconnect();
        try {

            /**
             * Retreiving data from database.
             */
            String query = "SELECT * FROM equipment_type WHERE 1";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            /**
             * creating the list of models EquipementType
             */
            equipementTypes = new ArrayList<>();

            /**
             * Feeding the list.
             */
            while(resultSet.next()){
                EquipementType eqt = new EquipementType(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));

                equipementTypes.add(eqt);
            }

        }catch(SQLException se){
            System.err.println("Une Erreur SQL est survenue");
            se.printStackTrace();
        }finally{
            /**
             * Disconnecting from database
             */
            dbclose();
        }
        return equipementTypes;
    }

    public EquipementType getTypeById(String id){

        dbconnect();

        EquipementType equipementType = null;

        try{
            String query = "SELECT * FROM equipment_type WHERE equipment_type_code = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                equipementType = new EquipementType(resultSet.getString("equipment_type_code"),resultSet.getString("equipment_type_lib"),resultSet.getString("equipment_famille"),resultSet.getString("equipment_categorie"));
            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }

        return equipementType;
    }

    public ArrayList<EquipementType> getEquipTypeByZipCode(String zipcode){
        dbconnect();
        ArrayList<EquipementType> equipementTypes  = null;
        try{
            String query = "";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1,zipcode);
            pstmt.setString(2,zipcode.substring(0,2)+"%");
            ResultSet rset = pstmt.executeQuery();
            equipementTypes = new ArrayList<>();
            while(rset.next()){
                equipementTypes.add(new EquipementType(rset.getString(1),rset.getString(2),rset.getString(3), rset.getString(4)));
            }
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            dbclose();
        }
        return equipementTypes;
    }





    public ArrayList<String> getEquipementTypesFamille(){
        dbconnect();
        try {
            String query = "SELECT DISTINCT equipment_famille FROM equipment_type";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            equipmentTypesFamille = new ArrayList<>();

            while(resultSet.next()){
                equipmentTypesFamille.add(resultSet.getString(1));
            }
        }catch(SQLException se){
            System.err.println("Une Erreur SQL est survenue");
            se.printStackTrace();
        }finally{
            dbclose();
        }
        return equipmentTypesFamille;
    }
    public static void main(String [] args){
        EquipementTypeDao etd = new EquipementTypeDao();
        ArrayList<EquipementType> etyps = etd.getEquipementTypes();
        EquipementType etyp = etd.getTypeById("2802");
        System.out.println(etyps);
        System.out.println(etyps.size());
        System.out.println(etyp);

    }

}
