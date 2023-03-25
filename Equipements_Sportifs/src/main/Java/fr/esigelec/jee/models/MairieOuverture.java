package fr.esigelec.jee.models;

public class MairieOuverture {

    private String insee;
    private int ouvertureId;
    private Mairie mairie;
    private Ouverture ouverture;

    public MairieOuverture(String insee, int ouvertureId){
        this.insee = insee;
        this.ouvertureId = ouvertureId;
    }

    public MairieOuverture(String insee, int ouvertureId,Mairie mairie, Ouverture ouverture){
        this(insee,ouvertureId);
        this.mairie = mairie;
        this.ouverture = ouverture;
    }

    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    public int getOuvertureId() {
        return ouvertureId;
    }

    public void setOuvertureId(int ouvertureId) {
        this.ouvertureId = ouvertureId;
    }
    public Mairie getMairie() {
        return mairie;
    }

    public void setMairie(Mairie mairie) {
        this.mairie = mairie;
    }

    public Ouverture getOuverture() {
        return ouverture;
    }

    public void setOuverture(Ouverture ouverture) {
        this.ouverture = ouverture;
    }
}
