package fr.esigelec.jee.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Ouverture implements Comparable<Ouverture>{
    private int ouverturId;
    private String plageJDebut;
    private String plageJFin;
    private Date plageHDebut;
    private Date plageHFin;
    private ArrayList<Mairie> mairiesRelated;

    public Ouverture(int ouverturId, String plageJDebut, String plageJFin, Date plageHDebut, Date plageHFin ) {
        this.ouverturId=ouverturId;
        this.plageJDebut = plageJDebut;
        this.plageJFin = plageJFin;
        this.plageHDebut = plageHDebut;
        this.plageHFin = plageHFin;
        mairiesRelated = new ArrayList<>();
    }

    /**
     *
     * @return Mairies of the time
     */
    public ArrayList<Mairie> getMairiesRelated(){
        return mairiesRelated;
    }

    public boolean addMairieRelated(Mairie mairie){
        boolean added = false;

        if(mairiesRelated.indexOf(mairie) == -1){
            mairiesRelated.add(mairie);
            added = true;
        }
        return added;
    }

    /**
     *
     * @return a list of related Mairies
     */
    public ArrayList<Mairie> getAllRelatedMairies(){
        return mairiesRelated;
    }

    public int getOuverturId() {
        return ouverturId;
    }

    public void setOuverturId(int ouverturId) {
        this.ouverturId = ouverturId;
    }

    public String getPlageJDebut() {
        return plageJDebut;
    }

    public void setPlageJDebut(String plageJDebut) {
        this.plageJDebut = plageJDebut;
    }

    public String getPlageJFin() {
        return plageJFin;
    }

    public void setPlageJFin(String plageJFin) {
        this.plageJFin = plageJFin;
    }

    public Date getPlageHDebut() {
        return plageHDebut;
    }

    public void setPlageHDebut(Date plageHDebut) {
        this.plageHDebut = plageHDebut;
    }

    public Date getPlageHFin() {
        return plageHFin;
    }

    public void setPlageHFin(Date plageHFin) {
        this.plageHFin = plageHFin;
    }

    @Override
    public String toString() {
        return "Ouverture{" +
                "ouverturId=" + ouverturId +
                ", plageJDebut='" + plageJDebut + '\'' +
                ", plageJFin='" + plageJFin + '\'' +
                ", plageHDebut=" + plageHDebut +
                ", plageHFin=" + plageHFin +
                ", mairiesRelated=" + mairiesRelated +
                '}';
    }

    @Override
    public int compareTo(Ouverture o) {
        HashMap<String,Integer> daysOfWeek = new HashMap<>();

        daysOfWeek.put("lundi",0);
        daysOfWeek.put("mardi",1);
        daysOfWeek.put("mercredi",2);
        daysOfWeek.put("jeudi",3);
        daysOfWeek.put("vendredi",4);
        daysOfWeek.put("samedi",5);
        daysOfWeek.put("dimanche",6);

        if(this.getPlageJDebut() == null)
            return -1;
        if( o.getPlageJDebut() == null)
            return 1;

        if(daysOfWeek.get(this.getPlageJDebut())<daysOfWeek.get(o.getPlageJDebut())){
            return -1;
        }else if(daysOfWeek.get(this.getPlageJDebut())>daysOfWeek.get(o.getPlageJDebut())){
            return 1;
        }
        return 0;
    }
}
