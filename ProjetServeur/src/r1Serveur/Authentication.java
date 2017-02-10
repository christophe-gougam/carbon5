package r1Serveur;

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

import org.json.JSONObject;

import org.json.JSONException;

import java.io.PrintWriter;

public class Authentication implements Runnable{
	
	private Socket socket = null;
	String in;
	private PrintWriter out = null;
	private boolean authentifier = false;
	public Thread t2;
	Connection con=null;
	Statement s=null;
	ResultSet rs=null;
	String prenom;
	String nom;
	String age;
	ArrayList<String> data = new ArrayList();
	
		
		public Authentication(Socket s, String in, PrintWriter out){
			 this.socket = s;
			 this.in = in;
			 this.out=out;
		}
		public void run() {
		
			try {
				
				String login = null;
				String mdp = null;
				
				try {
					
					ArrayList<String> result = LectureJson.LectureFichier(in);
					login = result.get(0);
					mdp = result.get(1);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Retrieving connection from pool");
				ConnectionPool pool = new ConnectionPool();
				con = pool.getConnectionFromPool();
				
				data = CarManager.authentication(con, login, mdp);
				if(data.get(0).equals("No data recieved")){
					data.add(new String("Wrong login or password"));
					String JsonMessage = EcritureJson.WriteJson("ErrorAuth", data);
					System.out.println("Sending JSON error to Client");
					out.println(JsonMessage);
					out.flush();
				}
				else{
					String JsonMessage = EcritureJson.WriteJson("GrantAuth", data);
					System.out.println("Sending JSON succès to Client");
					out.println(JsonMessage);
					out.flush();
				}
				System.out.println("Returning connection to pool");
			 	ConnectionPool.returnConnectionToPool(con);
			
			}catch(Exception e){
			System.out.println("Unable to authenticate");
			}

		}
}
		

