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

import java.io.PrintWriter;

/**
 * Class Authentication check login and password
 * @author Carbon5
 */
public class Authentication implements Runnable{
	
	final static Logger logger = Logger.getLogger(Authentication.class);
	private Socket socket = null;
	String in;
	private PrintWriter out = null;
	public Thread t2;
	Connection con=null;
	ArrayList<String> data = new ArrayList();
	String JsonMessage;
		
	/**
     * Class constructor
     * @param s
     * @param in
     * @param out 
     */
	public Authentication(Socket s, String in, PrintWriter out){
		 this.socket = s;
		 this.in = in;
		 this.out=out;
	}
	
	/**
    * Method check login and password
    * @see LectureJson.LectureFichier
    * @see EcritureJson.WriteJson
    * @see pool.getConnectionFromPool
    * @see ConnectionPool.returnConnectionToPool
    * @throws Exception if JSON not read properly
    */
	public void run() {
	
		try {
			
			String login = null;
			String mdp = null;
			
			try {
				//retrieve data input by user in client side
				ArrayList<String> result = LectureJson.LectureFichier(in);
				login = result.get(0);
				mdp = result.get(1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Retrieving connection from pool");
			ConnectionPool pool = new ConnectionPool();
			con = pool.getConnectionFromPool();
			//runs method to check in database and retrieve data to create user object and serialize it
			//data = CRUD.authentication(con, login, mdp);
			
			//check identifier to see if database found the user or not and sends data to client
			switch(data.get(0)){
			case("GrantAuth"):
				JsonMessage = EcritureJson.WriteJson("GrantAuth", data);
			logger.info("Sending JSON succès to Client");
				out.println(JsonMessage);
				out.flush();
			break;
			case("Erreur de mot de passe"):
				JsonMessage = EcritureJson.WriteJson("Erreur de mot de passe", data);
				logger.info("Erreur de mot de passe");
				out.println(JsonMessage);
				out.flush();
			break;
			}
			logger.info("Returning connection to pool");
		 	ConnectionPool.returnConnectionToPool(con);
		
		}catch(Exception e){
		logger.error("Unable to authenticate");
		}

	}
}
	

