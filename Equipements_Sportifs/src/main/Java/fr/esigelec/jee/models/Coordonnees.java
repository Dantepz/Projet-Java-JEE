package fr.esigelec.jee.models;

public class Coordonnees {
    private String telephone;
    private String mail;
    private String url;

    public Coordonnees(String telephone, String mail, String url) {
        this.telephone = telephone;
        this.mail = mail;
        this.url = url;
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
}
