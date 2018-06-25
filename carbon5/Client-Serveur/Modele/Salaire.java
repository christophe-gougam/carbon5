package Modele;

import Serveur.Controlleurs.Serveur;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Salaire {

    final static Logger logger = Logger.getLogger(Serveur.class);

    private int id;
    private int idUser;
    private int salaireBrut;
    private Date dateDebut;
    private Date dateFin;
    private int tempsContratMois;

    public Salaire() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getSalaireBrut() {
        return salaireBrut;
    }

    public void setSalaireBrut(int salaireBrut) {
        this.salaireBrut = salaireBrut;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getTempsContratMois() {
        return tempsContratMois;
    }

    public void setTempsContratMois(int tempsContratMois) {
        this.tempsContratMois = tempsContratMois;
    }

    public Salaire(int id, int idUser, int salaireBrut, Date dateDebut, Date dateFin, int tempsContratMois) {
        this.id = id;
        this.idUser = idUser;
        this.salaireBrut = salaireBrut;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tempsContratMois = tempsContratMois;
    }

    public static String serialize(Salaire salaire){
        String serialize = salaire.getIdUser()+"///"+salaire.getSalaireBrut()+"///"+salaire.getDateDebut()+"///"+salaire.getDateFin()+"///"+salaire.getTempsContratMois();
        return serialize;
    }

    public static Salaire unSerialize(String serial){
        logger.info("Enter unserilization");
        ArrayList values = new ArrayList();
        for (String retval: serial.split("///")){
            values.add(retval);
        }


        Date dateDeb=null;
        try {
            java.util.Date utilDate = new SimpleDateFormat("YYYY-MM-DD").parse(values.get(4).toString());
            dateDeb = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date dateFin=null;
        try {
            java.util.Date utilDate = new SimpleDateFormat("YYYY-MM-DD").parse(values.get(5).toString());
            dateFin = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        logger.info("Begin unserilization");
        Salaire salaire = new Salaire(Integer.parseInt(values.get(0).toString()),Integer.parseInt(values.get(1).toString()),Integer.parseInt(values.get(3).toString()), dateDeb,dateFin,Integer.parseInt(values.get(6).toString()));
        logger.info("Success unserilization");
        return salaire;
    }

}

