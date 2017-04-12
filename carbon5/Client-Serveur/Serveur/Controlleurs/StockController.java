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
	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	
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
		
		System.out.println("Retrieving connection from Pool");
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
	    		
	    		
				obj.setStock(obj.getStock()+quantite);
				System.out.println("Updating through DAO");
				ret=test.update(obj);
				if (ret){
					data.add("addEntryStockOK");
				}else{
					data.add("addEntryStockKO");
				}
			break;
			
			case("addOutStock"):
				SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
				date = formatter2.parse(result.get(0));
				namePart = result.get(1);
				Part obj2 = test.find(namePart);
				quantite = Integer.parseInt(result.get(1));
    		
				ret = test.addOutStock(obj2, quantite, date);
				//vérifier si la pièce existe, incrémenter le stock
    		
				//////@Thierno Problème de if à ce niveau pensez à declarer comme clé primaire le nom de la pièce dans la base///////////////
    		
    		
				obj2.setStock(obj2.getStock()-quantite);
				System.out.println("Updating through DAO");
				ret=test.update(obj2);
				if (ret){
					data.add("addOutStockOK");
				}else{
					data.add("addOutStockKO");
				}
			break;
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		switch(data.get(0)){
		
		case("addEntryStockOK"):	case ("addEntryStockKO"):
			JsonMessage = EcritureJson.WriteJson(data.get(0), data);
			System.out.println("Sending result entry stock");
			out.println(JsonMessage);
			out.flush();
		break;
		
		case("addOutStockOK"):	case("addOutStockKO"):
			JsonMessage = EcritureJson.WriteJson(data.get(0), data);
			System.out.println("Sending result entry stock");
			out.println(JsonMessage);
			out.flush();
	break;
		}
		System.out.println("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}


