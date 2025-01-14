package Serveur.Controlleurs;

import Modele.LectureJson;
import org.apache.log4j.Logger;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Class ProcessData
 *
 * @author Carbon5
 */
public class ProcessData implements Runnable {

    private ServerSocket serverSocket;
    private Socket socket = null;
    private Thread t;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String identifier = null;
    final static Logger logger = Logger.getLogger(ProcessData.class);
    Connection con = null;
    int retour;
    ArrayList<String> data = new ArrayList<String>();

    /**
     * Class constructor
     *
     * @param serverSocket
     */
    public ProcessData(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Method run,
     * accept the socket to bind server and client to transmit information
     * retrieves the JSON message from the client and directs with the identifier to the correct thread
     *
     * @see LectureJson.Identifier
     * @see getInputStream
     * @see getOutputStream
     */
    public void run() {
        try {
            while (true) {
                logger.info("Retrieving connection from Pool");

                con = ConnectionPool.getConnectionFromPool();
                logger.info("Retrieving client socket");

                socket = serverSocket.accept();
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                String message_distant = in.readLine();
                try {
                    logger.info("Reading JSON Client");
                    identifier = LectureJson.Identifier(message_distant);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // identifier in JSON recieved routes the action to be performed
                switch (identifier) {
                    case ("Authentication"):
                        t = new Thread(new Authentication(con, socket, message_distant, out));
                        t.start();
                        break;
                    case ("Search"):
                        t = new Thread(new CarController(con, message_distant, out));
                        t.start();
                        break;
                    case ("AjoutVehicule"):
                        t = new Thread(new CarController(con, message_distant, out));
                        t.start();
                        break;
                    case ("SelectAllStates"):
                        logger.info("Case Select all states");
                        t = new Thread(new CardStateController(con, message_distant, out));
                        t.run();
                        break;
                    case ("SelectFirstFromWaitList"):
                        logger.info("Case Select first from wait list");
                        t = new Thread(new RepairCardController(con, message_distant, out));
                        t.run();
                        break;
                    case ("SelectCarsDefect"):
                        logger.info("Case Select cars defect");
                        t = new Thread(new RepairCardController(con, message_distant, out));
                        t.run();
                        break;
                    case ("CreatePart"):
                        logger.info("Case create Part");
                        t = new Thread(new PartController(con, message_distant, out));
                        t.run();
                        break;
                    case ("ModificationPart"):
                        logger.info("Case ModificationPart Part");
                        t = new Thread(new PartController(con, message_distant, out));
                        t.run();
                        break;
                    case ("DeletePart"):
                        logger.info("Case Delete Part");
                        t = new Thread(new PartController(con, message_distant, out));
                        t.run();
                        break;
                    case ("SelectAllParts"):
                        logger.info("Case Select all parts");
                        t = new Thread(new PartController(con, message_distant, out));
                        t.run();
                        break;
                    case ("SelectAllParkings"):
                        logger.info("Case Select all parkings");
                        t = new Thread(new ParkingController(con, message_distant, out));
                        t.run();
                        break;
                    case ("getInfoCar_query1"):
                        logger.info("Case retrieving info car");
                        t = new Thread(new CarController(con, message_distant, out));
                        t.run();
                        break;

                    case ("getWorkflowCar_query2"):
                        logger.info("Case retrieving workflow complet car");
                        t = new Thread(new CarController(con, message_distant, out));
                        t.run();
                        break;

                    case ("getCumulDay_query3"):
                        logger.info("Case retrieving day cumulation");
                        t = new Thread(new CarController(con, message_distant, out));
                        t.run();
                        break;

                    case ("getManutentionnaires_query4"):
                        logger.info("Case retrieving warehousemen");
                        t = new Thread(new CarController(con, message_distant, out));
                        t.run();
                        break;
                    case ("LoadAllComboBox"):
                        logger.info("Case Select all type car");
                        t = new Thread(new CarController(con, message_distant, out));
                        t.run();
                        break;
                    case ("addEntryStock"):
                        logger.info("Case entry stock");
                        t = new Thread(new StockController(con, message_distant, out));
                        t.run();
                        break;
                    case ("addOutStock"):
                        logger.info("Case out stock");
                        t = new Thread(new StockController(con, message_distant, out));
                        t.run();
                        break;
                    case ("addPreferences"):
                        logger.info("Case add preferences");
                        t = new Thread(new PreferencesController(con, message_distant, out));
                        t.run();
                        break;
                    case ("SelectAllPreferences"):
                        logger.info("Case select preferences");
                        t = new Thread(new PreferencesController(con, message_distant, out));
                        t.run();
                        break;
                    case ("getWorkerPerformances"):
                        logger.info("Get worker performances");
                        t = new Thread(new PerformanceController(con, message_distant, out));
                        t.run();
                        break;
                    default:
                        logger.info("Fonctionnalit� non prise en charge pour le moment");
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
