package Modele;

import Serveur.Controlleurs.Serveur;
import org.apache.log4j.Logger;

import java.util.Date;

public class Operation {

    final static Logger logger = Logger.getLogger(Serveur.class);

    private int id;
    private int idUser;
    private int difficulte;
    private Date dateOperation;
    private int tempsConsacre;
    private  int tempsEstime;
    private int coutTotal;


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

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public int getTempsConsacre() {
        return tempsConsacre;
    }

    public void setTempsConsacre(int tempsConsacre) {
        this.tempsConsacre = tempsConsacre;
    }

    public int getTempsEstime() {
        return tempsEstime;
    }

    public void setTempsEstime(int tempsEstime) {
        this.tempsEstime = tempsEstime;
    }

    public int getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(int coutTotal) {
        this.coutTotal = coutTotal;
    }
}
