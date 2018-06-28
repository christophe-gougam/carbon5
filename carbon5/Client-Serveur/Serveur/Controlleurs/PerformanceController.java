package Serveur.Controlleurs;

import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.Operation;
import Modele.OperationDAO;
import org.apache.log4j.Logger;
import org.json.JSONException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

public class PerformanceController implements Runnable {

    private Connection con;
    private String in;
    private PrintWriter out;

    final static Logger logger = Logger.getLogger(PerformanceController.class);

    public PerformanceController(Connection con, String in, PrintWriter out) {
        this.con = con;
        this.in = in;
        this.out = out;
    }

    public void run() {
        OperationDAO operationDAO = new OperationDAO(this.con);
        try {
            ArrayList<String> inputContent = LectureJson.LectureFichier(in);
            int workerId = Integer.valueOf(inputContent.get(0));
            Operation operation = operationDAO.getPerformance(workerId);
            ArrayList<String> data = new ArrayList<>();
            data.add(String.valueOf(operation.getId()));
            data.add(String.valueOf(operation.getIdUser()));
            data.add(String.valueOf(operation.getDifficulte()));
            data.add(operation.getDateOperation().toString());
            data.add(String.valueOf(operation.getTempsConsacre()));
            data.add(String.valueOf(operation.getTempsEstime()));
            data.add(String.valueOf(operation.getCoutTotal()));
            String response = EcritureJson.WriteJson("addWorkerPerformances", data);

            logger.info("Sending worker #" + workerId + " performances to client.");
            out.println(response);
            out.flush();
        } catch (JSONException e) {
            logger.error(e);
        } finally {
            ConnectionPool.returnConnectionToPool(con);
        }
    }

}
