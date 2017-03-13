package r1Client.Controlleurs;

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

import r1Client.Modele.*;
import r1Client.Vues.Authentication;
import r1Client.Vues.Fenetre;

/**
 * Class Connection creating the connection
 * @author Carbon5
 */
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
	Car car;
	JFrame frame=null; 

	
	/**
     * Class constructor
     * @param socket
     * @param data
     * @param identifier
     * @param f 
     */
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
			System.out.println(json);
			out.println(json);
			out.flush();
			
			System.out.println("Reception du JSON envoyé du serveur");
			String reponse = in.readLine();
			try {
				
				repId = LectureJson.Identifier(reponse);
				System.out.println(repId);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(repId){
			case "Erreur de mot de passe" :
				//Pop up displaying error
				JOptionPane.showMessageDialog(frame, "Erreur: Mauvaise identification");
				Authentication auth = new Authentication();
			break;
			case "GrantAuth" :
				objet = new JSONObject(reponse);
		    	System.out.println("Afficage du JSON : ");
		    	System.out.println(reponse);
		     
		    	tableau = objet.getJSONArray("data");
		    	result = new ArrayList();
	    		for(int i = 0; i < tableau.length(); i++) {
	
	    			result.add((String) tableau.get(i));
	    		}
	    		System.out.println(result.get(1));
		    	user = User.unSerialize(result.get(1));
				JOptionPane.showMessageDialog(frame, "Bienvenue "+user.getFirstName());
				Fenetre ajout = new Fenetre();
				
			break;
			
			case "OKCarInput" :
				objet = new JSONObject(reponse);
		    	//System.out.println("Afficage du resultat de l'ajout vehicule : ");
		    	//System.out.println(reponse);
		    	tableau = objet.getJSONArray("data");
				//JOptionPane.showMessageDialog(frame, tableau.get(0));
		    	result = new ArrayList();
	    		for(int i = 0; i < tableau.length(); i++) {
	
	    			result.add((String) tableau.get(i));
	    		}
	    		car = Car.unSerialize(result.get(1));
	    		JOptionPane.showMessageDialog(frame, "Voiture "+car.getTypeVehicule()+ " ajoutée");
	    		//Fenetre newFenetre = new Fenetre();
	    		
			break;
			
			case "KOCarInput" :
				objet = new JSONObject(reponse);
		    	System.out.println("Afficage du resultat de l'ajout vehicule : ");
		    	System.out.println(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			default : 
				System.out.println("default");
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
