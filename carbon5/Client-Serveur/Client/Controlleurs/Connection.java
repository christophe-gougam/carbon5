package Client.Controlleurs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Modele.Car;
import Modele.Defect;
import Modele.EcritureJson;
import Modele.LectureJson;
import Modele.Parking;
import Modele.Part;
import Modele.Place;
import Modele.RepairCard;
import Modele.TypeCar;
import Modele.User;
import Serveur.Controlleurs.Serveur;
import Vues.Authentication;
import Vues.IHM;
import Vues.PanStat;

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
	private JSONArray tableautypecar;
	private JSONArray tableau;
	private JSONArray tableaudefect;
	private JSONArray tableaudPlace;
	User user;
	Car car;
	JPanel frame=null; 
        public static JPanel panStat;

	
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
			
			logger.info("Reception du JSON envoy� du serveur");
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
		    	
		    	if(!User.isInCollection(user.getLogin())){
	    			User.addAUserToCo(user);
	    		}
		    	
				JOptionPane.showMessageDialog(frame, "Bienvenue "+user.getFirstName());
                                int p = user.getTypeUser().getId();
                                
                                if(p == 2){
                                    panStat = new PanStat(p);
                                    logger.info("MASQUER TAB 4");
                                } else {
                                    panStat = new PanStat(p);
                                    logger.info("DISPLAY TAB 4");  
                                }
                                IHM ihm = new IHM();
                                
                                
				
			break;
			
			case "OKCarInput" :
				objet = new JSONObject(reponse);
		    	tableau = objet.getJSONArray("data");
		    	result = new ArrayList<String>();
	    		for(int i = 0; i < tableau.length(); i++) {
	
	    			result.add((String) tableau.get(i));
	    		}
	    		car = Car.unSerialize(result.get(1));
	    		if(result.get(result.size()-1).equalsIgnoreCase("Journee"))
	    			JOptionPane.showMessageDialog(frame, "Voiture "+car.getTypeVehicule()+" ajoutee"+"\n"+"Date previsionnelle : "+result.get(2)+"\n");
	    		else
	    			JOptionPane.showMessageDialog(frame, "Voiture "+car.getTypeVehicule()+" ajoutee"+"\n"+"Date previsionnelle : "+result.get(2)+"\n A midi\n");	
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
				logger.info("Afficage du resultat de mise � jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case "DeletePartOK"	: case "DeletePartKO":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise � jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case "SelectAllPartsOK":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise � jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
		    	for (int i =0; i<tableau.getInt(1);i++){
		    		Part aPart = Part.unSerialize(tableau.getString(i+2));
		    		if(!Part.isInCollection(aPart.getIdPart())){
		    			Part.addPartToCo(aPart);
		    		}	
		    	}
                        break;
                        
                        case "SelectAllParkingsOK":
				objet = new JSONObject(reponse);
				logger.info("Affichage du resultat de mise à jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
		    	for (int i =0; i<tableau.getInt(1);i++){
		    		Parking aParking = Parking.unSerialize(tableau.getString(i+2));
		    		if(!Parking.isInCollection(aParking.getNumParking())){
		    			Parking.addParkingToCo(aParking);
		    		}	
		    	}
                        break;
                        
                        case "query1_OK":
				objet = new JSONObject(reponse);
				logger.info("Affichage du resultat de mise à jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
		    	for (int i =0; i<tableau.getInt(1);i++){
		    		RepairCard aRepairCard = RepairCard.unSerialize(tableau.getString(i+2));
		    		if(!RepairCard.isInCollection(aRepairCard.getidcard())){
		    			RepairCard.addRepairCardToCo(aRepairCard);
		    		}	
		    	}
                        break;
                        
//                        case "query2_OK":
//				objet = new JSONObject(reponse);
//				logger.info("Affichage du resultat de mise à jour : ");
//		    	logger.info(reponse);
//		    	tableau = objet.getJSONArray("data");
//		    	for (int i =0; i<tableau.getInt(1);i++){
//		    		Parking aParking = Parking.unSerialize(tableau.getString(i+2));
//		    		if(!Parking.isInCollection(aParking.getNumParking())){
//		    			Parking.addParkingToCo(aParking);
//		    		}	
//		    	}
//                        break;
//                        
//                        case "query3_OK":
//				objet = new JSONObject(reponse);
//				logger.info("Affichage du resultat de mise à jour : ");
//		    	logger.info(reponse);
//		    	tableau = objet.getJSONArray("data");
//		    	for (int i =0; i<tableau.getInt(1);i++){
//		    		Parking aParking = Parking.unSerialize(tableau.getString(i+2));
//		    		if(!Parking.isInCollection(aParking.getNumParking())){
//		    			Parking.addParkingToCo(aParking);
//		    		}	
//		    	}
//                        break;
//                        
//                        case "query4_OK":
//				objet = new JSONObject(reponse);
//				logger.info("Affichage du resultat de mise à jour : ");
//		    	logger.info(reponse);
//		    	tableau = objet.getJSONArray("data");
//		    	for (int i =0; i<tableau.getInt(1);i++){
//		    		Parking aParking = Parking.unSerialize(tableau.getString(i+2));
//		    		if(!Parking.isInCollection(aParking.getNumParking())){
//		    			Parking.addParkingToCo(aParking);
//		    		}	
//		    	}
//                        break;
                        
		    //rechercher vehicule avec sa puce
			case "SearchOK":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de la recherche : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
		    	
	    		Car ca=Car.unSerialize(tableau.getString(1));
	    		if(!Car.isInCollection(ca.getNumePuce())){
	    			Car.addCar(ca);
	    		}	
		    	
		    break;
			case "SearchKO":
				JOptionPane.showMessageDialog(frame, "Cette reference ne correspond pas aun aucun vehicule");
		    break;
			case "CarNotExist":
				JOptionPane.showMessageDialog(frame, "Ce vehicule existe pas, contacter votre administrateur");
		    break;
			case "AlreadyAdded":
				JOptionPane.showMessageDialog(frame, "Ce vehiule est deja en reparation.\n Contacter votre administrateur");
		    break;
		    
		    //rechercher vehicule avec sa puce
//			case "SearchOK":
//				objet = new JSONObject(reponse);
//				logger.info("Afficage du resultat de la recherche : ");
//		    	logger.info(reponse);
//		    	tableau = objet.getJSONArray("data");
//		    	
//		    		Car ca=Car.unSerialize(tableau.getString(1));
//		    		///TODO
//		    	
//		    break;
//			case "SearchKO":
//				JOptionPane.showMessageDialog(frame, "Cette r�f�rence ne correspond pas � un aucun vehicule");
//		    break;
//		    
//			case "CarNotExist":
//				JOptionPane.showMessageDialog(frame, "Ce vehivule existe pas, contacter votre administrateur");
//		    break;
			case "LoadAllComboBoxOK" :
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat");
		    	logger.info(reponse);
                tableau = objet.getJSONArray("allCar");
		    	tableautypecar = objet.getJSONArray("data");
		    	tableaudefect = objet.getJSONArray("dataDefect");
		    	tableaudPlace = objet.getJSONArray("placement");
		    	for (int k =0; k<tableau.getInt(0);k++){
		    		Car aCar = Car.unSerialize(tableau.getString(k+1));
		    		if(!Car.isInCollection(aCar.getNumePuce())){
		    			Car.addCarToCo(aCar);
		    		}
                        }
                        for (int i =0; i<tableautypecar.getInt(1);i++){
		    		TypeCar atype = TypeCar.unSerialize(tableautypecar.getString(i+2));
		    		if(!TypeCar.isInCollection(atype.getType())){
		    			TypeCar.addPartToCo(atype);
		    		}
		    	}
	    		for (int ii =0; ii<tableaudefect.getInt(0);ii++){
		    		Defect adefect = Defect.unSerialize(tableaudefect.getString(ii+1));
		    		if(!Defect.isInCollection(adefect.getDescription())){
		    			Defect.addPartToCo(adefect);
		    		}	
		    	}
	    		for (int iii =0; iii<tableaudPlace.getInt(0);iii++){
		    		Place aplace = Place.unSerialize(tableaudPlace.getString(iii+1));
		    		if(!Place.isInCollection(aplace.getNumPlace())){
		    			Place.addplaceToCo(aplace);
		    		}	
		    	}
		    break;
			case "addEntryStockOK":	case "addEntryStockKO":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise � jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case "addOutStockOK":	case "addOutStockKO":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise � jour : ");
		    	logger.info(reponse);
		    	tableau = objet.getJSONArray("data");
				JOptionPane.showMessageDialog(frame, tableau.get(0));
			break;
			case "addPreferencesOK" : case "addPreferencesKO":
				objet = new JSONObject(reponse);
				logger.info("Afficage du resultat de mise � jour : ");
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
