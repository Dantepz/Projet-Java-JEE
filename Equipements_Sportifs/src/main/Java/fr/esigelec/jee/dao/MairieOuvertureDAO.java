package fr.esigelec.jee.dao;

import fr.esigelec.jee.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MairieOuvertureDAO extends DAO{

    public MairieOuvertureDAO(){
        super();
    }

    private MairieDAO mdao;
    private OuvertureDAO odao;
    private void init(){
        mdao= new MairieDAO();
        odao = new OuvertureDAO();
    }

    private ArrayList<Mairie> mairies;
    private ArrayList<Ouverture> ouvertures;
    private void feedData(String zipcode){
        mairies = mdao.getMairiesByZipCode(zipcode,0,1000);
        ouvertures = odao.getDeptOuvertures(zipcode);
    }

    public ArrayList<Ouverture> getOuvsOfMairie(String zipcode, Mairie mairie){
        init();
        feedData(zipcode);

        ArrayList<Ouverture> ouvertureOfMairie = new ArrayList<>();

        for(Ouverture o : ouvertures){
            if (o.getMairiesRelated().contains(mairie)){
                ouvertureOfMairie.add(o);
            }
        }

        return ouvertureOfMairie;

    }

    public ArrayList<Mairie> getMairiesByOuvs(String zipcode, Ouverture ouverture){
        init();
        feedData(zipcode);

        ArrayList<Mairie> mairiesByOuv = new ArrayList<>();

        for(Mairie m : mairies) {
            if (m.getOuverture().contains(ouverture)) {
                mairiesByOuv.add(m);
            }
        }
        return mairiesByOuv;
    }


}
