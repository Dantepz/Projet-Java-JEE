package fr.esigelec.jee.data;

/**
 * Fichier permettant d'importer un fichier CSV dans une table MySQL
 * Ce Script utilise le mode transactionnel afin de respecter l'approche ACID (Atomicité, Cohérence, Isolation, Durabilité)
 * qui permet d'assurer l'intégrité des données au sein d'une base de données.
 * @author Serais Sébastien
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;

public class ExcelToMysql {
    // nom de la machine hôte qui héberge le SGBD Mysql
    final static String host = "localhost";
    // nom de la BDD sur le serveur Mysql
    final static String nomBase = "equipements_sportifs";
    // login de la BDD
    final static String login = "admin";
    // mot de passe
    final static String motDePasse = "admin";
    // chemin fichier csv à importer
    final static String nomFichier = "Equipements_Sportifs/src/main/data/cvs/equipements_clean.csv";
    // caractère de séparation des colonne
    final static String separateur = ";";
    final static String nomTable = "equipement_sportif";

    public static void main(String[] args) {
        Connection con = null;			//objet connexion
        PreparedStatement stmt = null;	//prepareStatement
        int compteurLignesAjoutees = 0;	//compteur de lignes effectivement ajoutées
        String ligne = null;			//ligne lue dans le fichier
        String[] tab;					//tableau issu du split
        int retour;						//nb de lignées insérées
        Scanner sc = new Scanner(System.in);
        String reponse;

        // chargement du pilote MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e2) {

            System.err.println("pilote mysql non trouvé : com.mysql.cj.jdbc.Driver");
            System.exit(-1);
        }

        try {
            // Connexion avec choix de l'encodage
            con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + nomBase + "?characterEncoding=UTF-8",
                    login, motDePasse);

            // activation du mode transactionnel
            con.setAutoCommit(false);
            // Creation du flux de lecture en mode caractères
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nomFichier), StandardCharsets.UTF_8));
            // nombre de lignes insérées dans le BDD
            br.readLine();
            do {

                try {
                    ligne = br.readLine();
                    if (ligne != null) {
                        System.out.println(ligne);

                        tab = ligne.split(separateur);
                        System.out.println("nb colonnes=" + tab.length);
                        tab[6] = tab[6].replace(',','.');
                        tab[7] = tab[7].replace(',','.');
                        tab[8] = tab[8].replace(',','.');
                        System.out.println(tab[6]+", "+tab[7]+", "+tab[8]);

                        for (String s : tab) {

                            System.out.print(s + "**");
                        }
                        System.out.println();

                        // TODO configurer la requete SQL en fonction des colonnes
                        String requete = "insert into " + nomTable + "(equipment_id,equ_nom,com_insee,ins_numero_install,ins_nom,nature_libelle, equ_surface_evolution,equip_gpsx,equip_gpsy,equipment_type_code_id) values (?,?,?,?,?,?,?,?,?,?)";
                        stmt = con.prepareStatement(requete);
                        System.out.println(requete);

                        // TODO injection des données dans la requete au format souhaité
                        stmt.setString(1, tab[3]);
                        stmt.setString(2, tab[4]);
                        stmt.setString(3, tab[0]);
                        stmt.setString(4, tab[1]);
                        stmt.setString(5, tab[2]);
                        stmt.setString(6, tab[5]);
                        if(!(tab[6].isEmpty())) {
                            stmt.setFloat(7, Float.parseFloat(tab[6]));
                        }
                        else{
                            stmt.setNull(7, Types.FLOAT);
                        }
                        if(!(tab[7].isEmpty())) {
                            stmt.setFloat(8, Float.parseFloat(tab[7]));
                        }
                        else{
                            stmt.setNull(8, Types.FLOAT);
                        }
                        if(!(tab[8].isEmpty())) {
                            stmt.setFloat(9, Float.parseFloat(tab[8]));
                        }
                        else{
                            stmt.setNull(9, Types.FLOAT);
                        }
                        stmt.setString(10,tab[9]);

                        // exécution de la requete
                        retour = stmt.executeUpdate();
                        // incrémentation du nb de lignées insérées
                        compteurLignesAjoutees = compteurLignesAjoutees + retour;

                        if (retour != 1) {
                            System.out.println("**********************************ERREUR ..." + requete);
                            throw new Exception();
                        }

                    }
                } catch (SQLIntegrityConstraintViolationException doublon) {

                    System.out.println("doublon ignoré ...");
                    doublon.printStackTrace();
                }

            } while (ligne != null);

            // On demande si l'utilisateur souhaite valider la transaction complète
            System.out.println("VALIDER les requetes ? (O/N)");

            reponse = sc.nextLine();
            if ("O".equalsIgnoreCase(reponse)) {
                System.out.println("COMMIT");
                con.commit();
                System.out.println("lignes ajoutées : " + compteurLignesAjoutees);
            } else {
                System.out.println("ROLLBACK");
                con.rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("ROLLBACK");
                con.rollback();

            } catch (SQLException e1) {

                e1.printStackTrace();
            }

            e.printStackTrace();
        } finally {

            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

}
