package Serveur.Controlleurs;

import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.Salaire;
import Modele.SalaireDAO;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SalaireController implements Runnable {

    String in;
    private PrintWriter out = null;
    public Thread t2;
    final static Logger logger = Logger.getLogger(SalaireController.class);

    Connection con = null;
    ArrayList<String> data = new ArrayList<String>();
    String JsonMessage;
    LocalDate date;
    int id = 0;
    int idUser = 0;
    int salaireBrut = 0;
    Date dateDebut;
    Date dateFin;
    int tempsContratMois;
    boolean ret;

    public SalaireController(Connection con, String in, PrintWriter out) {
        this.con = con;
        this.in = in;
        this.out = out;
    }

    public void run() {


        SalaireDAO test = new SalaireDAO(con);
        try {
            String identifier = LectureJson.Identifier(in);
            ArrayList<String> result = LectureJson.LectureFichier(in);
            switch (identifier) {


                case ("ModificationSalaire"):
                    id = Integer.parseInt(result.get(0));
                    salaireBrut = Integer.parseInt(result.get(3));
                    Salaire salaireUpdate = new Salaire(id, idUser, salaireBrut, dateDebut, dateFin, tempsContratMois);
                    logger.info("Updating through DAO");
                    ret = test.update(salaireUpdate);
                    if (ret)
                        data.add("ModificationSalaireOK");
                    else
                        data.add("ModificationSalaireKO");
                    break;
                case ("SelectSalaire"):
                    //data = test.find(salaireBrut);
                    data.add(0, "SelectSalaireOK");
                    break;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch (data.get(0)) {
            case ("ModificationSalaireOK"):
                JsonMessage = EcritureJson.WriteJson("ModificationSalaireOK", data);
                logger.info("Sending JSON succ�s to Client");
                out.println(JsonMessage);
                out.flush();
                break;
            case ("ModificationSalaireKO"):
                JsonMessage = EcritureJson.WriteJson("ModificationSalaireKO", data);
                logger.info("Erreur lors de la mise � jour de ce salaire");
                out.println(JsonMessage);
                out.flush();
                break;
            case ("SelectSalaireOK"):
                JsonMessage = EcritureJson.WriteJson("SelectSalaireOK", data);
                logger.info("Sending salary to Client");
                out.println(JsonMessage);
                out.flush();
                break;
        }
        logger.info("Returning connection to pool");
        ConnectionPool.returnConnectionToPool(con);
    }
}
