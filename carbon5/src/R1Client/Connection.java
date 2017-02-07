package R1Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class Connection implements Runnable{
	
	private Socket socket = null;
	public static Thread t2;
	private PrintWriter out;
	private BufferedReader in;
	private ArrayList<String> data;
	private String identifier;
	
	public Connection(Socket socket, ArrayList<String> data, String identifier){
		socket = socket;
		data = data;
		identifier = identifier;
	}
	
	public void run(){
		
		try{
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String json = EcritureJson.WriteJson(identifier, data);
			out.println(json);
			
			String reponse = LectureJson.Identifier(in);
			if(reponse.equals("ErrorAuth")){
				//faire une popup d'erreur
				JOptionPane.showMessageDialog(null, "Erreur: Mauvaise identification");
			}else if(reponse.equals("GrantAuth")){
				//faire une popup d'acceptation d'auth qui avec ok renvoie au menu
				String nom = data.get(0);
				String prenom = data.get(1);
				String age = data.get(2);
				String login = data.get(3);
				JOptionPane.showMessageDialog(null, "Bienvenue "+nom+" "+prenom+" "+age+" ans, vous êtes connecté avec le login "+login);
				Fenetre postAuth = new Fenetre();
			}
			else{
				System.out.println("Exception non taitée");
			}
			
		}catch (Exception e){
			
		}
		
	}
}
