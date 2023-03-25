package fr.esigelec.jee.models;

public class Adresse {

    private int id;
    private String ligne;
    private int codePostal;
    private String commune;
    private double latitude;
    private double longitude;
    private double precision;

    private String insee;

    public Adresse(int id, String ligne, int codePostal, String commune, double latitude, double longitude, double precision, String insee) {
        this.id = id;
        this.ligne = ligne;
        this.codePostal = codePostal;
        this.commune = commune;
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
        this.insee = insee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        commune = commune;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", ligne='" + ligne + '\'' +
                ", codePostal=" + codePostal +
                ", commune='" + commune + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", precision=" + precision +
                ", insee='" + insee + '\'' +
                '}';
    }
}
