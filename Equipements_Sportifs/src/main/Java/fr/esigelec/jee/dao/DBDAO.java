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
    public PreparedStatement preparedStatement;

    /**
     * Constructor.
     */
    public DBDAO() {
        dbConnect();
    }

    /**
     * Method used to return the country name from a country code.
     * @param countryCode the code of a country.
     * @return The name of the country.
     */
    public String getCountryName(String countryCode) {
        String countryName;
        switch(countryCode) {
            case "fr":
                countryName = "France";
                break;
            case "es":
                countryName = "Spain";
                break;
            case "pt":
                countryName = "Portugal";
                break;
            default:
                countryName = "Country not found";
                break;
        }
        return countryName;
    }

    /**
     * Method used to return the capital name from a country code.
     * @param countryCode the code of a country.
     * @return the capital of a country with a country code.
     */
    public String getCityName(String countryCode) {
        String countryCity;
        switch(countryCode) {
            case "fr":
                countryCity = "Paris";
                break;
            case "es":
                countryCity = "Madrid";
                break;
            case "pt":
                countryCity = "Lisbon";
                break;
            default:
                countryCity = "City not found";
                break;
        }
        return countryCity;
    }

    /**
     * Method used to create a list with the country codes inside of it.
     * @return ArrayList with the country codes of all the lines in the database.
     */
    public ArrayList<String> getCountryCodes() {
        ArrayList<String> cr = new ArrayList<String>();
        try {
            connection = DriverManager.getConnection(mySQLURL, username, password);

            statement = connection.createStatement();
            String query = "SELECT ccode FROM `country_capitals`;";
            ResultSet resultSet = statement.executeQuery(query);

            hashMap = new HashMap<>();
            while (resultSet.next()) {
                cr.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cr;
    }

    /**
     * Method used to connect an user with its login / password and compare it from them from the db.
     * @param name the login.
     * @param pass the password.
     * @return true if connected and false if not.
     */
    public boolean validateUserCredentials(String name, String pass) {
        String sqlUser = "";
        String sqlPassword = "";
        pass = hashPassword(pass);
        try {

            statement = connection.createStatement();
            String query = "SELECT * FROM `user`"
                    + "WHERE name='" + name + "' AND password = '" + pass + "';";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                sqlUser = resultSet.getString(2);
                sqlPassword = resultSet.getString(4);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sqlUser.equals(name) && sqlPassword.equals(pass);
    }

    /**
     * Method used to initialize a connection to the database.
     */
    private static void dbConnect() {
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
     * Method used to hash password before send it in the database.
     * @param password the clear password that we want to hash.
     * @return the hashed password.
     */
    public String hashPassword(String password) {
        MessageDigest digest;
        StringBuilder hexString = new StringBuilder();

        try {
            digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    /**
     * Method used to add a new country in the database.
     * @param country the name of the country.
     * @param capital the name of the capital.
     * @return true if ok and false if there is an error.
     */
    public boolean addNewCountry(String country, String capital) {
        boolean state = false;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `country_capitals` (ccode, cname, ccapital) VALUES (?, ?, ?)");
            preparedStatement.setString(1, country.substring(0, 2).toLowerCase()); // getting the two firsts characters from the country name to save it as the country code.
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, capital);

            state = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
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
