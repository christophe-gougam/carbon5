package r1Client;

import java.io.BufferedReader;


import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import r1Serveur.Authentication;
//import r1Serveur.CarManager;
//import r1Serveur.EcritureJson;
//import r1Serveur.LectureJson;

public class Connection{
	
	private Socket socket = null;
	public static Thread t2;
	private PrintWriter out;
	private BufferedReader in;
	private ArrayList<String> data;
	private ArrayList<String> result;
	private String identifier;
	private String repId;
	private JSONObject objet;
	private JSONArray tableau;
	User user;
	JFrame frame=null; 
	public Connection(Socket socket, ArrayList<String> data, String identifier, JFrame f){
		this.socket = socket;
		this.data = data;
		this.identifier = identifier;
		this.frame=f;
		
		try{
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String json = EcritureJson.WriteJson(identifier, data);
			System.out.println("Envoie du JSON au serveur");
			out.println(json);
			out.flush();
			String reponse = in.readLine();
			try {
				
				System.out.println("Reception du JSON envoyé du serveur");
				repId = LectureJson.Identifier(reponse);
				System.out.println(repId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(repId){
			case("Erreur de mot de passe"):
				//faire une popup d'erreur
				JOptionPane.showMessageDialog(frame, "Erreur: Mauvaise identification");
			break;
			case("GrantAuth"):
				objet = new JSONObject(reponse);
		    	System.out.println("Afficage du JSON : ");
		    	System.out.println(reponse);
		     
		    	tableau = objet.getJSONArray("data");
		    	result = new ArrayList();
	    		for(int i = 0; i < tableau.length(); i++) {
	
	    			result.add((String) tableau.get(i));
	    		}
	   
		    	user = User.unSerialize(result.get(1));
				JOptionPane.showMessageDialog(frame, "Bienvenue "+user.getFirstName());
			break;
			case("QueryAjoutSuccess"):
				objet = new JSONObject(reponse);
		    	System.out.println("Afficage du resultat de l'ajout vehicule : ");
		    	System.out.println(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case("Erreur_Ajout"):
				objet = new JSONObject(reponse);
		    	System.out.println("Afficage du resultat de l'ajout vehicule : ");
		    	System.out.println(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
