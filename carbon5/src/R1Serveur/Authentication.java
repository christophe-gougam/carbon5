package R1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
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
	private BufferedReader in = null;
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
	
		
		public Authentication(Socket s, BufferedReader in){
			 socket = s;
			 in = in;
		}
		public void run() {
		
			try {
				out = new PrintWriter(socket.getOutputStream());
				
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
				con = ConnectionPool.getConnectionFromPool();
				
				while(!authentifier){
			
					data = CarManager.authentication(con, login, mdp);
					if(data.get(0) == "Aucune donnée reçue"){
						data.add(new String("Login ou mot de passe erroné"));
						String JsonMessage = EcritureJson.WriteJson("ErrorAuth", data);
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
					}
				}
			 	ConnectionPool.returnConnectionToPool(con);
			
			}catch(Exception e){
			System.out.println("Blabla");
			}
		}
}
		

