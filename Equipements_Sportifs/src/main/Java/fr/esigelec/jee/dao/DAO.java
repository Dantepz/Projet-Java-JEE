package fr.esigelec.jee.dao;

import java.sql.*;

public class DAO {
    protected Connection con;

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
     * Connect to the database
     */
    public void dbconnect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/equipements_sportifs?characterEncoding=UTF-8", "admin", "admin");
            System.out.println("Connexion établie");
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dbclose(PreparedStatement pstmt){
        dbclose();
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void dbclose(ResultSet rset){
        if(rset != null) {
            try {
                rset.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String [] args){
        DAO dao = new DAO();
        dao.dbconnect();
        dao.dbclose();
    }
}
