package fr.esigelec.jee.models;

public class Coordonnee {
    private int coordineesId;
    private String telephone;
    private String mail;
    private String url;

    private String insee;

    public Coordonnee(int coordineesId,String telephone, String mail, String url, String insee) {
        this.coordineesId = coordineesId;
        this.telephone = telephone;
        this.mail = mail;
        this.url = url;
        this.insee = insee;
    }

    public int getCoordineesId() {
        return coordineesId;
    }

    public void setCoordineesId(int coordineesId) {
        this.coordineesId = coordineesId;
    }

    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Coordonnee{" +
                "coordineesId=" + coordineesId +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", url='" + url + '\'' +
                ", insee='" + insee + '\'' +
                '}';
    }
}
