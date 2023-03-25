package fr.esigelec.jee.models;

import java.util.ArrayList;
import java.util.Objects;

public class Mairie {
    private String insee;
    private String nom;
    private Adresse adresse;
    private Coordonnee coordonnee;
    private ArrayList<Ouverture> ouverture;

    public Mairie(String insee, String nom, Adresse adresse, Coordonnee coordonnee) {
        this.insee = insee;
        this.nom = nom;
        this.adresse = adresse;
        this.coordonnee = coordonnee;
        this.ouverture = new ArrayList<>();
    }

    public Mairie(String insee){
        this(insee,null,null,null);
    }

    public void addOuverture(Ouverture ouverture){
        this.ouverture.add(ouverture);
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

    public Coordonnee getCoordonnees() {
        return coordonnee;
    }

    public void setCoordonnees(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
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
                ", nom='" + nom + '\'' +
                ", adresse=" + adresse +
                ", coordonnee=" + coordonnee +
                ", ouverture=" + ouverture +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mairie mairie = (Mairie) o;
        return insee.equals(mairie.insee);
    }
}
