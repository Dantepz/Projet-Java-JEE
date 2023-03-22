package fr.esigelec.jee.models;

import java.util.Date;

public class Ouverture {
    private Date plageJDebut;
    private Date plageJFin;
    private Date plageHDebut;
    private Date plageHFin;

    public Ouverture(Date plageJDebut, Date plageJFin, Date plageHDebut, Date plageHFin) {
        this.plageJDebut = plageJDebut;
        this.plageJFin = plageJFin;
        this.plageHDebut = plageHDebut;
        this.plageHFin = plageHFin;
    }

    public Date getPlageJDebut() {
        return plageJDebut;
    }

    public void setPlageJDebut(Date plageJDebut) {
        this.plageJDebut = plageJDebut;
    }

    public Date getPlageJFin() {
        return plageJFin;
    }

    public void setPlageJFin(Date plageJFin) {
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
}
