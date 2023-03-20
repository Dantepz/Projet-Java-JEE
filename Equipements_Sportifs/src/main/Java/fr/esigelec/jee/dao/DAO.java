package fr.esigelec.jee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    private Connection con;

    /**
     * Verifying the connection is well established.
     */
    public DAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = null;
        }catch (ClassNotFoundException cnfe){
            System.err.println("Erreur : Ne peut trouver le connecteur");
        }
    }

    /**
     * @return the connector of the DAO instance.
     */
    public Connection getCon(){
        return con;
    }

    /**
     * Connect to the database
     */
    public void dbconnect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipements_sportifs?characterEncoding=UTF-8", "admin", "admin");
            System.out.println("Conection établie");
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }

    /**
     * Closing the connection
     */
    public void dbclose() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String [] args){
        DAO dao = new DAO();
        dao.dbconnect();
        dao.dbclose();
    }
}
