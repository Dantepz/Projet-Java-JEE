package fr.esigelec.jee.models;

import java.util.ArrayList;

public class Mairie {
    private String nom;
    private Adresse adresse;
    private Coordonnees coordonnees;
    private ArrayList<Ouverture> ouverture;

    public Mairie(String nom, Adresse adresse, Coordonnees coordonnees) {
        this.nom = nom;
        this.adresse = adresse;
        this.coordonnees = coordonnees;
        this.ouverture = new ArrayList<>();
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
}
