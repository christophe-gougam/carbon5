package Serveur.Controlleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;

import Modele.LectureJson;
import Modele.EcritureJson;
import Modele.Part;
import Modele.PartDAO;

public class StockController implements Runnable{
	
	private Socket socket = null;
	String in;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(StockController.class);

	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	
	int stocknew = 0;
	float purchasePrice = 0;
	int quantite = 0;
	String namePart = null;
	DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
	Date date = new Date();
	boolean ret;
	
	public StockController(Socket s, String in, PrintWriter out){
		this.socket = s;
		 this.in = in;
		 this.out=out;
	}
	
public void run() {
		
		logger.info("Retrieving connection from Pool");
		ConnectionPool pool = new ConnectionPool();
		con = pool.getConnectionFromPool();
		PartDAO test = new PartDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){
			
			case("addEntryStock"):
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				date = formatter.parse(result.get(0));
	    		namePart = result.get(1);
	    		Part obj = test.find(namePart);
	    		quantite = Integer.parseInt(result.get(1));
	    		
	    		ret = test.addEntryStock(obj, quantite, date);
				//vérifier si la pièce existe, incrémenter le stock
	    		
	    		//////@Thierno Problème de if à ce niveau pensez à declarer comme clé primaire le nom de la pièce dans la base///////////////
	    		
	    		
				
				Part partUpdate = new Part(stocknew, namePart, purchasePrice);
				logger.info("Updating through DAO");
				ret=test.update(partUpdate);
				if (ret){
					data.add("addEntryStockOK");
				}else{
					data.add("addEntryStockKO");
				}
			break;
			
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		switch(data.get(0)){
		case("CreatePartOK"):
			JsonMessage = EcritureJson.WriteJson("CreatePartOK", data);
			logger.info("Sending JSON succès createPart to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("CreatePartKO"):
			JsonMessage = EcritureJson.WriteJson("CreatePartKO", data);
			logger.error("Erreur lors de l'ajout de Part");
			out.println(JsonMessage);
			out.flush();
		break;
		case("ModificationPartOK"):
			JsonMessage = EcritureJson.WriteJson("ModificationPartOK", data);
			logger.info("Sending JSON succès to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("ModificationPartKO"):
			JsonMessage = EcritureJson.WriteJson("ModificationPartKO", data);
			logger.error("Erreur lors de la mise à jour de cette pièce");
			out.println(JsonMessage);
			out.flush();
		break;
		case("SelectAllPartsOK"):
			JsonMessage = EcritureJson.WriteJson("SelectAllPartsOK", data);
			logger.info("Sending list of part to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("addEntryStockOK"):
			JsonMessage = EcritureJson.WriteJson("addEntryStockOK", data);
			logger.info("Sending success entry stock");
			out.println(JsonMessage);
			out.flush();
		break;
		}
		logger.info("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}


