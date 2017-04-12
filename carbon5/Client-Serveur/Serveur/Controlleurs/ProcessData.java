package Serveur.Controlleurs;

import Modele.LectureJson;
import Modele.EcritureJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.PrintWriter;

/**
 * Class ProcessData
 * @author Carbon5
 */
public class ProcessData implements Runnable{
	
	private ServerSocket serverSocket;
	private Socket socket = null;
	private Thread t;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String identifier = null;
	final static Logger logger = Logger.getLogger(ProcessData.class);
	Connection con=null;
	int retour;
	ArrayList<String> data = new ArrayList();
	
	   /**
     * Class constructor
     * @param serverSocket 
     */
	public ProcessData(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
	
	/**
     * Method run,
     * accept the socket to bind server and client to transmit information
     * retrieves the JSON message from the client and directs with the identifier to the correct thread
     * @see LectureJson.Identifier
     * @see getInputStream
     * @see getOutputStream
     * 
     */
	public void run(){
		try {
			while(true){
				System.out.println("Retrieving connection from Pool");
				ConnectionPool pool = new ConnectionPool();
				con = pool.getConnectionFromPool();
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
				switch(identifier){
				case("Authentication"):
					t = new Thread(new Authentication(con, socket, message_distant, out));
					t.start();
				break;
				case("AjoutVehicule"):
					t = new Thread(new CarController(con, socket, message_distant, out));
					t.start();
				break;
				case("CreatePart"):
					logger.info("Case create Part");
					t = new Thread(new PartController(con, message_distant, out));
					t.run();
				break;
				case("ModificationPart"):
					logger.info("Case ModificationPart Part");
					t = new Thread(new PartController(con, message_distant, out));
					t.run();
				break;
				case("SelectAllParts"):
					logger.info("Case Select all parts");
					t = new Thread(new PartController(con, message_distant, out));
					t.run();
				break;
				case("addEntryStock"):
					logger.info("Case entry stock");
					t = new Thread(new StockController(con, message_distant, out));
					t.run();
				break;
				case("addOutStock"):
					logger.info("Case out stock");
					t = new Thread(new StockController(con, message_distant, out));
					t.run();
				break;
				default:
					logger.info("Fonctionnalité non prise en charge pour le moment");
				}

				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
