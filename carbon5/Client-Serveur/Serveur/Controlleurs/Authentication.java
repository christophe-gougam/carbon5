package Serveur.Controlleurs;

import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONException;

import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.User;
import Modele.UserDAO;

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
	public Authentication(Connection con, Socket s, String in, PrintWriter out){
		 this.con=con;
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
		UserDAO test = new UserDAO(con);
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
			//runs method to check in database and retrieve data to create user object and serialize it
			
			User testUser = test.auth(login, mdp);
			if(testUser.getFirstName() != null){
				data.add("GrantAuth");
				data.add(User.serialize(testUser));
			}else{
				data.add("Erreur de mot de passe");
			}
			//check identifier to see if database found the user or not and sends data to client
			switch(data.get(0)){
			case("GrantAuth"):
				JsonMessage = EcritureJson.WriteJson("GrantAuth", data);
			logger.info("Sending JSON succ�s to Client");
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
	

