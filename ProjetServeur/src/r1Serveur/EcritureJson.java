package r1Serveur;
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EcritureJson {
 
//    public static void main(String[] args) throws JSONException {
//	// Génération du JSON depuis un tableau d'objets
//	//Personne p =  new Personne("DIALLO", "Thierno", 30,"DThierno","123");
//    	
//	JSONObject objet = new JSONObject();
//	
//	
//	objet.put("data", new JSONArray(p));
//	objet.put("identifier", "Authentication");
//
//	// Création du fichier de sortie
//	FileWriter fs = null;
//	try {
//		fs = new FileWriter("TEST_IO.txt");
//		objet.write(fs);
//	    fs.flush();
//	} catch(IOException e) {
//	    System.err.println("Erreur lors de l'ouverture du fichier ");
//	    System.err.println(e);
//	    System.exit(-1);
//	}
//    }
    
	//changer type renvoyé, doit etre un string
    public static String WriteJson(String identifier, ArrayList<String> data){
    	String identifier2 = identifier;
    	JSONObject json = new JSONObject();
    	
    	try {
			json.put("data", new JSONArray(data));
			json.put("identifier", identifier2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//convertir le json en String
    	String file = json.toString();
    	return file;
    	
    }
}