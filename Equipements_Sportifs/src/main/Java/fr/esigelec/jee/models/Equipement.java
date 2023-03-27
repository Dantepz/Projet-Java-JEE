package fr.esigelec.jee.models;

public class Equipement {
    private String id;
    private String nom;
    private String insee;
    private String insNumInstall;
    private String inNom;
    private String natureLibelle;
    private float equSurfEvol;
    private float equipGpsx;
    private float equipGpsy;
    private EquipementType equipementType;
    public Equipement(String id, String nom, String insee, String insNumInstall, String inNom, String natureLibelle, float equSurfEvol, float equipGpsx, float equipGpsy, EquipementType equipementType) {
        this.id = id;
        this.nom = nom;
        this.insee = insee;
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

    public String getMairie() {
        return insee;
    }

    public void setMairie(String insee) {
        this.insee = insee;
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

    public void setEquipTypeCodeId(EquipementType equipementType) {
        this.equipementType = equipementType;
    }

    @Override
    public String toString() {
        return "Equipements{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", insee='" + insee + '\'' +
                ", insNumInstall='" + insNumInstall + '\'' +
                ", inNom='" + inNom + '\'' +
                ", natureLibelle='" + natureLibelle + '\'' +
                ", equSurfEvol=" + equSurfEvol +
                ", equipGpsx=" + equipGpsx +
                ", equipGpsy=" + equipGpsy +
                ", equipementType=" + equipementType +
                '}';
    }
}
