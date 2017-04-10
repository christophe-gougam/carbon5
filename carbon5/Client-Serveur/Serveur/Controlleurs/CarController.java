package Serveur.Controlleurs;

import java.io.BufferedReader;

import Modele.LectureJson;
import Modele.EcritureJson;

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

import org.json.JSONObject;
import org.json.JSONException;

import java.io.PrintWriter;

/**
 * Class CarController runs operation for car 
 * @author Carbon5
 */
public class CarController implements Runnable{
	private Socket socket = null;
	String in;
	private PrintWriter out = null;
	public Thread t2;
	Connection con=null;
	String matriculation = null;
	String type = null;
	String statut = null;
	String parking = null;

	ArrayList<String> data = new ArrayList();
	String JsonMessage;
	
	/**
	 * Method constructor of controller
	 */
	public CarController(Socket s, String in, PrintWriter out){
		 this.socket = s;
		 this.in = in;
		 this.out=out;
	}
	/**
	 * Method run gets connection form pool and insert into database
     * @see LectureJson.LectureFichier
     * @see EcritureJson.WriteJson
     * @see pool.getConnectionFromPool
     * @see ConnectionPool.returnConnectionToPool
     * @throws Exception if JSON not read properly
	 */
	public void run() {
				
				try{
					ArrayList<String> result = LectureJson.LectureFichier(in);
					matriculation = result.get(0);
					type = result.get(1);
					statut = result.get(2);
					parking = result.get(3);
				}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
			System.out.println("Retrieving connection from pool");
			ConnectionPool pool = new ConnectionPool();
			con = pool.getConnectionFromPool();
			
			//sends data to CRUD to insert new Car create object and serialize it to give to client side
			//data = CRUD.addCar(con, matriculation, type, statut, parking);
			
			switch(data.get(0)){
			case("Voiture enregistrée"):
				JsonMessage = EcritureJson.WriteJson("OKCarInput", data);
				System.out.println("Sending JSON succès to Client");
				out.println(JsonMessage);
				out.flush();
			break;
			case("Erreur lors de l'execution de la requête"):
				JsonMessage = EcritureJson.WriteJson("KOCarInput", data);
				System.out.println("Erreur de mot de passe");
				out.println(JsonMessage);
				out.flush();
			break;
			}
			//returns connection to pool
			System.out.println("Returning connection to pool");
		 	ConnectionPool.returnConnectionToPool(con);
	}
	
}
