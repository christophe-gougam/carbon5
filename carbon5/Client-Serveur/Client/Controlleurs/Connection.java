package Client.Controlleurs;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Vues.Authentication;
import Vues.Fenetre;
import Vues.IHM;
import Modele.*;
import Serveur.Controlleurs.Serveur;

/**
 * Class Connection creating the connection
 * @author Carbon5
 */
public class Connection{
	final static Logger logger = Logger.getLogger(Serveur.class);
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
	JPanel frame=null; 

	
	/**
     * Class constructor
     * @param socket
     * @param data
     * @param identifier
     * @param f 
     */
	public Connection(Socket socket, ArrayList<String> data, String identifier, JPanel f){
		this.socket = socket;
		this.data = data;
		this.identifier = identifier;
		this.frame=f;
		
		try{
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String json = EcritureJson.WriteJson(identifier, data);
			logger.info("Envoie du JSON au serveur");
			logger.info(json);
			out.println(json);
			out.flush();
			
			logger.info("Reception du JSON envoyé du serveur");
			String reponse = in.readLine();
			try {
				
				repId = LectureJson.Identifier(reponse);
				logger.info(repId);
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
		    	logger.info("Afficage du JSON : ");
		    	logger.info(reponse);
		     
		    	tableau = objet.getJSONArray("data");
		    	result = new ArrayList();
	    		for(int i = 0; i < tableau.length(); i++) {
	
	    			result.add((String) tableau.get(i));
	    		}
	    		logger.info(result.get(1));
		    	user = User.unSerialize(result.get(1));
		    	User.addAUserToCo(user);
				JOptionPane.showMessageDialog(frame, "Bienvenue "+user.getFirstName());
				IHM ihm = new IHM();
				
			break;
			
			case "OKCarInput" :
				objet = new JSONObject(reponse);
		    	//logger.info("Afficage du resultat de l'ajout vehicule : ");
		    	//logger.info(reponse);
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
		    	logger.info("Afficage du resultat de l'ajout vehicule : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			
			case "CreatePartOK" : case "CreatePartKO" : 
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de l'ajout vehicule : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			
			case "ModificationPartOK" : case "ModificationPartKO" : 
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise à jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case "DeletePartOK"	: case "DeletePartKO":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise à jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case "SelectAllPartsOK":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise à jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
		    	int indice = 2;
		    	for (int i =0; i<tableau.getInt(1);i++){
		    		Part aPart = Part.unSerialize(tableau.getString(indice));
		    		if(!Part.isInCollection(aPart.getIdPart())){
		    			Part.addPartToCo(aPart);
		    		}	
		    		indice++;
		    	}
		    break;
			case "addEntryStockOK":	case "addEntryStockKO":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise à jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case "addOutStockOK":	case "addOutStockKO":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise à jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			default : 
				logger.info("default");
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
