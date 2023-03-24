package fr.esigelec.jee.models;

public class Equipements{
    private String id;
    private String nom;
    private Mairie mairie;
    private String insNumInstall;
    private String inNom;
    private String natureLibelle;
    private float equSurfEvol;
    private float equipGpsx;
    private float equipGpsy;
    private EquipementType equipementType;
    public Equipements(String id, String nom, Mairie mairie, String insNumInstall, String inNom, String natureLibelle, float equSurfEvol, float equipGpsx, float equipGpsy, EquipementType equipementType) {
        this.id = id;
        this.nom = nom;
        this.mairie = mairie;
        this.insNumInstall = insNumInstall;
        this.inNom = inNom;
        this.natureLibelle = natureLibelle;
        this.equSurfEvol = equSurfEvol;
        this.equipGpsx = equipGpsx;
        this.equipGpsy = equipGpsy;
        this.equipementType = equipementType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Mairie getMairie() {
        return mairie;
    }

    public void setMairie(String comInsee) {
        this.mairie = mairie;
    }

    public String getInsNumInstall() {
        return insNumInstall;
    }

    public void setInsNumInstall(String insNumInstall) {
        this.insNumInstall = insNumInstall;
    }

    public String getInNom() {
        return inNom;
    }

    public void setInNom(String inNom) {
        this.inNom = inNom;
    }

    public String getNatureLibelle() {
        return natureLibelle;
    }

    public void setNatureLibelle(String natureLibelle) {
        this.natureLibelle = natureLibelle;
    }

    public float getEquSurfEvol() {
        return equSurfEvol;
    }

    public void setEquSurfEvol(float equSurfEvol) {
        this.equSurfEvol = equSurfEvol;
    }

    public float getEquipGpsx() {
        return equipGpsx;
    }

    public void setEquipGpsx(float equipGpsx) {
        this.equipGpsx = equipGpsx;
    }

    public float getEquipGpsy() {
        return equipGpsy;
    }

    public void setEquipGpsy(float equipGpsy) {
        this.equipGpsy = equipGpsy;
    }

    public EquipementType getEquipTypeCodeId() {
        return equipementType;
    }

    public void setEquipTypeCodeId(String equipTypeCodeId) {
        this.equipementType = equipementType;
    }
}
