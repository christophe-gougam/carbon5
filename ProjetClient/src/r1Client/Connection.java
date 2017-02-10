package r1Client;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Connection implements Runnable{
	
	private Socket socket = null;
	public static Thread t2;
	private PrintWriter out;
	private BufferedReader in;
	private ArrayList<String> data;
	private String identifier;
	String repId;
	
	public Connection(Socket socket, ArrayList<String> data, String identifier){
		this.socket = socket;
		this.data = data;
		this.identifier = identifier;
	}
	
	public void run(){
		
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
			if(repId.equals("ErrorAuth")){
				//faire une popup d'erreur
				JOptionPane.showMessageDialog(null, "Erreur: Mauvaise identification");
			}else if(repId.equals("GrantAuth")){
				JSONObject objet = new JSONObject(reponse);
		    	System.out.println("Afficage du JSON : ");
		    	System.out.println(reponse);
		     
		    	JSONArray tableau = objet.getJSONArray("data");
		    	ArrayList<String> result = new ArrayList();
		    	System.out.println(tableau.length());
	    		for(int i = 0; i < tableau.length(); i++) {

	    			result.add((String) tableau.get(i));
	    		}
	   
		    	User user = User.unSerialize(result.get(0));
				JOptionPane.showMessageDialog(null, "Bienvenue "+user.getFirstName());
			}
			else{
				System.out.println("Exception non taitée");
			}
			
		}catch (Exception e){
			
		}
		
	}
}
