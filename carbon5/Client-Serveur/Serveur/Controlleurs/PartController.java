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

import java.io.PrintWriter;
public class PartController implements Runnable{
	
	private Socket socket = null;
	String in;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(PartController.class);

	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	
	String IdPart = null;
	String namePart = null;
	Float purchasePrice = null;
	boolean ret;
	public PartController(Socket s, String in, PrintWriter out){
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
			
			case("CreatePart"):
			
				
	    		namePart = result.get(0);
	    		purchasePrice = Float.parseFloat(result.get(1));
	    		Part res=test.find(namePart);
	    		int stock=res.getStock();
	    		int stocknew=stock+1;
				//v�rifier si la pi�ce existe, incr�menter le stock
	    		
	    		//////@Thierno Probl�me de if � ce niveau pensez � declarer comme cl� primaire le nom de la pi�ce dans la base///////////////
	    		
	    		if (res.getPurchasePrice()==purchasePrice){
					Part partUpdate = new Part(stocknew, namePart, purchasePrice);
					logger.info("Updating through DAO");
					ret=test.update(partUpdate);
					if (ret)
						data.add("CreatePartOK");
					else
						data.add("CreatePartKO");
				}
				//sinon ajouter la pi�ce
				else{
					Part partToAdd = new Part(1, namePart, purchasePrice);
					logger.info("Putting through DAO");
					data = test.create(partToAdd);
				}
			break;
			case("ModificationPart"):
				IdPart=result.get(0);
				namePart = result.get(1);
				purchasePrice = Float.parseFloat(result.get(2));
				Part partUpdate = new Part(IdPart, namePart, purchasePrice);
				logger.info("Updating through DAO");
				ret=test.update(partUpdate);
				if (ret)
					data.add("ModificationPartOK");
				else
					data.add("ModificationPartKO");
			break;
			case("SelectAllParts"):
				data = test.getAllParts();
				data.add(0, "SelectAllPartsOK");
			break;
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		switch(data.get(0)){
		case("CreatePartOK"):
			JsonMessage = EcritureJson.WriteJson("CreatePartOK", data);
		logger.info("Sending JSON succ�s createPart to Client");
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
		logger.info("Sending JSON succ�s to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("ModificationPartKO"):
			JsonMessage = EcritureJson.WriteJson("ModificationPartKO", data);
		logger.error("Erreur lors de la mise � jour de cette pi�ce");
			out.println(JsonMessage);
			out.flush();
		break;
		case("SelectAllPartsOK"):
			JsonMessage = EcritureJson.WriteJson("SelectAllPartsOK", data);
		logger.info("Sending list of part to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		}
		logger.info("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}
