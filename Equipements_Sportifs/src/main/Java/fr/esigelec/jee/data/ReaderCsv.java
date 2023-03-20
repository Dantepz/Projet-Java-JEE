package fr.esigelec.jee.data;

import java.io.*;
import java.sql.SQLIntegrityConstraintViolationException;

public class ReaderCsv {

    public static void read0(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException{
        String ligne;
        String [] tab;
        int j = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            // nombre de lignes insérées dans le BDD

            do {
                ligne = br.readLine();
                if (ligne != null) {
                    System.out.println(ligne);

                    tab = ligne.split(";");
                    System.out.println("nb colonnes=" + tab.length);

                    for (int i = 0; i < tab.length; i++) {

                        System.out.print(tab[i] + "**");
                    }
                    System.out.println("");
                }
                j++;
            } while (ligne != null && j<1000);
        }catch(IOException ee){
            ee.printStackTrace();
        }
    }

    public static void main(String [] args) throws IOException {
        read0("/Users/bernardombangyandoumbe/Downloads/projet jee files/Csv/equipements_clean.csv");
    }
}
