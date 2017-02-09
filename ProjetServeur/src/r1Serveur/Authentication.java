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
				///test sans le pool juste pour voir les reponses du cote client et cote serveur///////////////////////
				nom = "DIALLO";
				prenom = "THierno";
				age = "100";
				login = "test";
				String MessageReturned = nom +" "+prenom+" "+age+" ans est connecté avec le login"+login;
				data.add(MessageReturned);
				String JsonMessage = EcritureJson.WriteJson("GrantAuth", data);
				System.out.println("Envoie du JSON erreur au Client");
				System.out.println(MessageReturned);
				out.println(JsonMessage);
				out.flush();
				/*System.out.println("Récupération d'une connexion par le pool");
				
				con = ConnectionPool.getConnectionFromPool();
				data = CarManager.authentication(con, login, mdp);
				if(data.get(0) == "Aucune donnée reçue"){
					data.add(new String("Login ou mot de passe erroné"));
					String JsonMessage = EcritureJson.WriteJson("ErrorAuth", data);
					System.out.println("Envoie du JSON erreur au Client");
					out.println(JsonMessage);
					out.flush();
				}
				else{
					
					nom = data.get(0);
					prenom = data.get(1);
					age = data.get(2);
					login = data.get(3);
					String MessageReturned = nom +" "+prenom+" "+age+" ans est connecté avec le login"+login;
					data.add(MessageReturned);
					String JsonMessage = EcritureJson.WriteJson("GrantAuth", data);
					out.println(JsonMessage);
					out.flush();
					System.out.println("Envoie ju JSON succès au Client");
				}
				System.out.println("Retour de la connexion au pool");
			 	ConnectionPool.returnConnectionToPool(con);
			*/
			}catch(Exception e){
			System.out.println("Blabla");
			}
		}
}
		

