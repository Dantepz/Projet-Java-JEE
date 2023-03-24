package fr.esigelec.jee.dao;

import java.sql.*;
import java.util.HashMap;

/**
 * Class use to link the java webApp with the database.
 * @author Valentin Leclerc
 * @version 1.9
 */
public class DBDAO {

    private static final String mySQLURL = "jdbc:mysql://localhost/equipements_sportifs";
    private static final String username = "ccodes";
    private static final String password = "javaee2021";

    private static HashMap<String, String> hashMap;

    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;

    /**
     * Constructor.
     */
    public DBDAO() {
        dbConnect();
    }

    public void selectStatement() {
        try {
            connection = DriverManager.getConnection(mySQLURL, username, password);

            statement = connection.createStatement();
            String query = "SELECT ccode FROM `country_capitals`;";
            ResultSet resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to initialize a connection to the database.
     */
    public static void dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(mySQLURL, username, password);

            statement = connection.createStatement();
            String query = "SELECT mairie_id, mairie_nom FROM `mairie`;";
            ResultSet resultSet = statement.executeQuery(query);

            hashMap = new HashMap<>();
            while (resultSet.next()) {
                //hashMap.put(resultSet.getString(1), resultSet.getString(2));
                System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        }
    }

    /**
     * Method used to close the connection to the database.
     */
    public static void dbClose() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        dbConnect();
        dbClose();
    }
}
