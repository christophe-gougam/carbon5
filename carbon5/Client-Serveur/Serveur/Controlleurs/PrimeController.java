package Serveur.Controlleurs;

import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.Prime;
import Modele.PrimeDAO;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PrimeController implements Runnable{

    String in;
    private PrintWriter out = null;
    public Thread t2;
    final static Logger logger = Logger.getLogger(SalaireController.class);

    Connection con=null;
    ArrayList<String> data = new ArrayList<String>();
    String JsonMessage;
    LocalDate date;
    int id = 0;
    int idUser = 0;
    LocalDate dateAttribution;
    int montantPrime;

    boolean ret;

    public PrimeController(Connection con, String in, PrintWriter out){
        this.con=con;
        this.in = in;
        this.out=out;
    }

    public void run() {


        PrimeDAO test = new PrimeDAO(con);
        try{
            String identifier = LectureJson.Identifier(in);
            ArrayList<String> result = LectureJson.LectureFichier(in);
            switch(identifier){


                case("CreatePrime"):
                    dateAttribution = LocalDate.now();
                    montantPrime = Integer.parseInt(result.get(1));
                    Prime primeToAdd = new Prime(1, idUser, dateAttribution,montantPrime);
                    logger.info("Putting through DAO");
                    data = test.create(primeToAdd);
                    break;

            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch(data.get(0)){
            case("CreatePrimeOK"):
                JsonMessage = EcritureJson.WriteJson("CreatePrimeOK", data);
                logger.info("Sending JSON succ�s to Client");
                out.println(JsonMessage);
                out.flush();
                break;
            case("CreatePrimeKO"):
                JsonMessage = EcritureJson.WriteJson("CreatePrimeKO", data);
                logger.info("Erreur lors de la création de cette prime");
                out.println(JsonMessage);
                out.flush();
                break;

        }
        logger.info("Returning connection to pool");
        ConnectionPool.returnConnectionToPool(con);
    }
}
