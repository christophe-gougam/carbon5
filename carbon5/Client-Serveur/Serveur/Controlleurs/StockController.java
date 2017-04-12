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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.json.JSONObject;
import org.json.JSONException;

import Modele.LectureJson;
import Modele.EcritureJson;
import Modele.Part;
import Modele.PartDAO;
import Modele.User;
import Modele.UserDAO;

public class StockController implements Runnable{
	
	String in;
	private PrintWriter out = null;
	public Thread t2;
	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	
	int quantite = 0;
	String namePart = null;
	LocalDate date;
	boolean ret;
	
	public StockController(Connection con, String in, PrintWriter out){
		this.con=con;
		 this.in = in;
		 this.out=out;
	}
	
public void run() {
		User us;
		PartDAO test = new PartDAO(con);
		UserDAO user = new UserDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){
			
			case("addEntryStock"):
				date = LocalDate.now();
	    		namePart = result.get(0);
	    		Part obj = test.find(namePart);
	    		us=user.find();
	    		quantite = Integer.parseInt(result.get(1));
	    		
	    		ret = test.addEntryStock(us, obj, quantite, date);
				if (ret){
					data.add("addEntryStockOK");
				}else{
					data.add("addEntryStockKO");
				}
			break;
			
			case("addOutStock"):
				date = LocalDate.now();
				namePart = result.get(0);
				Part obj2 = test.find(namePart);
				quantite = Integer.parseInt(result.get(1));
				us=user.find();
				ret = test.addOutStock(us, obj2, quantite, date);
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
			System.out.println("Sending result out stock");
			out.println(JsonMessage);
			out.flush();
			break;
		}
		System.out.println("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}


