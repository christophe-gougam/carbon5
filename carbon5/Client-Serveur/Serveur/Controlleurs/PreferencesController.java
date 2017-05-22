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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import Modele.Preferences;
import Modele.PreferencesDAO;
import Modele.RepairCard;

import java.io.PrintWriter;

public class PreferencesController implements Runnable{
	
	String in;
	private PrintWriter out = null;
	public Thread t2;
	final static Logger logger = Logger.getLogger(PartController.class);

	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	
	float indifDays = 0;
	float vetoDays = 0;
	float indifTime = 0;
	float vetoTime = 0;
	
	boolean ret;
	
	public PreferencesController(Connection con, String in, PrintWriter out){
		this.con=con;
		this.in = in;
		this.out=out;
	}
	
	public void run() {
		
		PreferencesDAO test = new PreferencesDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){
			
			case("addPreferences"):
	    		indifDays = Float.parseFloat(result.get(0));
				vetoDays = Float.parseFloat(result.get(1));
				indifTime = Float.parseFloat(result.get(2));
				vetoTime = Float.parseFloat(result.get(3));
				Preferences prefToAdd = new Preferences(1, indifDays, vetoDays, indifTime, vetoTime);
				logger.info("Putting through DAO");
				data = test.create(prefToAdd);
			break;
			case("SelectAllPreferences"):
				data = test.getAllPreferences();
				data.add(0, "SelectAllPreferencesOK");
			break;
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		
		switch(data.get(0)){
		case("addPreferencesOK"):
			JsonMessage = EcritureJson.WriteJson("addPreferencesOK", data);
			logger.info("Sending JSON succès createPart to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("SelectAllPreferencesOK"):
			JsonMessage = EcritureJson.WriteJson("SelectAllPreferencesOK", data);
			logger.info("Sending JSON succès createPart to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		}
		logger.info("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}
