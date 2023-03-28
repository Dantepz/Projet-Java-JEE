package fr.esigelec.jee.models;

import fr.esigelec.jee.dao.EquipementDAO;

import java.util.ArrayList;
import java.util.Objects;

public class Mairie implements Comparable<Mairie>{
    private String insee;
    private String nom;
    private Adresse adresse;
    private Coordonnee coordonnee;
    private ArrayList<Ouverture> ouverture;

    private ArrayList<Equipement> equipements;

    public Mairie(String insee, String nom, Adresse adresse, Coordonnee coordonnee) {
        this.insee = insee;
        this.nom = nom;
        this.adresse = adresse;
        this.coordonnee = coordonnee;
        this.ouverture = new ArrayList<>();
        this.equipements = new ArrayList<>();
    }

    public Mairie(String insee, String nom){
        this(insee,nom,null,null);
    }

    public void addOuverture(Ouverture ouverture){
        this.ouverture.add(ouverture);
    }

    public void addEquipment(Equipement equipement){this.equipements.add(equipement);}

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

    public ArrayList<Equipement> getEquipements() {
        return equipements;
    }

    public int getEquipementsSize(){
        return equipements.size();
    }

    public Equipement getEquipement(int i){

        Equipement eq = equipements.get(i);
        return eq;
    }

    public ArrayList<String> getEquipNoms(){
        ArrayList<String> noms = new ArrayList<>();
        for(int i =0;i<equipements.size();i++) {
            noms.add(this.equipements.get(i).getNom());
        }
        return noms;
    }
    @Override
    public String toString() {
        return "Mairie{" +
                "insee='" + insee + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse=" + adresse +
                ", coordonnee=" + coordonnee +
                ", ouverture=" + ouverture +
                ", equipements=" + equipements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mairie mairie = (Mairie) o;
        return insee.equals(mairie.insee);
    }

    @Override
    public int compareTo(Mairie o) {
        return this.nom.compareTo(o.getNom());
    }

    public boolean containsAKeyword(String myString, String search){
            if(myString.contains(search)) {
                return true;
            }
        return false;
    }

    public Mairie getMairie(Equipement eq){
        if(equipements.contains(eq)){
            return this;
        }
        return null;
    }
}
