package fr.esigelec.jee.models;

import java.util.ArrayList;

public class Mairie {
    private String insee;
    private String nom;
    private Adresse adresse;
    private Coordonnees coordonnees;
    private ArrayList<Ouverture> ouverture;

    public Mairie(String insee, String nom, Adresse adresse, Coordonnees coordonnees) {
        this.insee = insee;
        this.nom = nom;
        this.adresse = adresse;
        this.coordonnees = coordonnees;
        this.ouverture = null;
    }

    public Mairie(String insee){
        this.insee = insee;
        this.nom = null;
        this.adresse = null;
        this.coordonnees = null;
        this.ouverture = null;
    }


    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public ArrayList<Ouverture> getOuverture() {
        return ouverture;
    }
    public void setOuverture(ArrayList<Ouverture> ouverture) {
        this.ouverture = ouverture;
    }

    @Override
    public String toString() {
        return "Mairie{" +
                "insee='" + insee + '\'' +
                '}';
    }
}
