package fr.esigelec.jee.models;

public class Adresse {

    private int id;
    private String ligne;
    private int codePostal;
    private String Commune;
    private double latitude;
    private double longitude;
    private double precision;

    public Adresse(int id, String ligne, int codePostal, String commune, double latitude, double longitude, double precision) {
        this.id = id;
        this.ligne = ligne;
        this.codePostal = codePostal;
        this.Commune = commune;
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
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
        return Commune;
    }

    public void setCommune(String commune) {
        Commune = commune;
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
}
