package fr.esigelec.jee.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;
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
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
        }

        try {
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

    /**
     * Method used to select and old country and capital and the edit it with new values.
     * @param oldCountry the old country name.
     * @param oldCapital the old capital name.
     * @param newCountry the new country name.
     * @param newCapital the new capital name.
     * @return true if all is good and false if there is an error.
     */
    public boolean editCountry(String oldCountry, String oldCapital, String newCountry, String newCapital) {
        boolean state = false;
        try {
            preparedStatement = connection.prepareStatement("UPDATE `country_capitals` SET ccode = ?, cname = ?, ccapital = ? WHERE ccode = ? AND cname = ? AND ccapital = ?");
            preparedStatement.setString(1, newCountry.substring(0, 2).toLowerCase()); // getting the two firsts characters from the country name to save it as the country code.
            preparedStatement.setString(2, newCountry);
            preparedStatement.setString(3, newCapital);
            preparedStatement.setString(4, oldCountry.substring(0, 2).toLowerCase()); // getting the two firsts characters from the country name to save it as the country code.
            preparedStatement.setString(5, oldCountry);
            preparedStatement.setString(6, oldCapital);

            state = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    /**
     * Method used to delete a country from the database.
     * @param country the country name to delete.
     * @param capital the capital name to delete.
     * @return true if all is good and false if there is an error.
     */
    public boolean deleteCountry(String country, String capital) {
        boolean state = false;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `country_capitals` WHERE ccode = ? AND cname = ? AND ccapital = ?;");
            preparedStatement.setString(1, country.substring(0, 2).toLowerCase()); // getting the two firsts characters from the country name to save it as the country code.
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, capital);

            state = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    public static void main(String[] args) {
        dbConnect();
        dbClose();
    }
}
