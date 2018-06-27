package Modele;

import Serveur.Controlleurs.Serveur;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Prime {

    final static Logger logger = Logger.getLogger(Serveur.class);


    private int id;
    private int id_user;
    private LocalDate date_attribution;
    private int montant_prime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public LocalDate getDate_attribution() {
        return date_attribution;
    }

    public void setDate_attribution(LocalDate date_attribution) {
        this.date_attribution = date_attribution;
    }

    public int getMontant_prime() {
        return montant_prime;
    }

    public void setMontant_prime(int montant_prime) {
        this.montant_prime = montant_prime;
    }

    public Prime(int id, int id_user, LocalDate date_attribution, int montant_prime) {
        this.id = id;
        this.id_user = id_user;
        this.date_attribution = date_attribution;
        this.montant_prime = montant_prime;
    }

    public static String serialize(Prime prime){
        String serialize = prime.getId()+"///"+prime.getId_user()+"///"+prime.getDate_attribution()+"///"+prime.getMontant_prime();
        return serialize;
    }

    public static Prime unSerialize(String serial){
        logger.info("Enter unserilization");
        ArrayList values = new ArrayList();
        for (String retval: serial.split("///")){
            values.add(retval);
        }


        LocalDate dateAttrib=null;
        try {
            java.util.Date utilDate = new SimpleDateFormat("YYYY-MM-DD").parse(values.get(2).toString());
            dateAttrib =  LocalDate.now();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("Begin unserilization");
        Prime prime = new Prime(Integer.parseInt(values.get(0).toString()),Integer.parseInt(values.get(1).toString()), dateAttrib,Integer.parseInt(values.get(3).toString()));
        logger.info("Success unserilization");
        return prime;
    }

}
