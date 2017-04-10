package Modele;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
 
public class LectureJson {
 
	public static String Identifier(String in) throws JSONException {
		JSONObject objet = new JSONObject(in);
		String identifier = (String) objet.get("identifier");
		
		return identifier;
	}
	
    public static ArrayList<String> LectureFichier(BufferedReader fs) throws JSONException {
 
    	
    	
    	StringBuilder sb= new StringBuilder();
    	String line = "";

    	try {
			while ((line = fs.readLine()) != null) {
			    sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	String json = sb.toString();
	// Récupération de la chaïne JSON depuis le fichier
//	String json = new String();
//	Scanner scanner = new Scanner(fs);
//	System.out.println("Reading BufferedReader");
//	while(scanner.hasNext())
//	    json += scanner.nextLine();
//	scanner.close();
	
	// Création d'un objet JSON
	JSONObject objet = new JSONObject(json);
	System.out.println("Afficage du JSON : ");
	System.out.println(json);
 
	// Traitement du fichier reçu
	System.out.println("\nparcours du fichier Json :");
	JSONArray tableau = objet.getJSONArray("data");
	ArrayList<String> result = new ArrayList();
	String identifier = (String) objet.get("identifier");
	
	switch(identifier){
	
	case("authentication"):
		
		for(int i = 0; i < tableau.length(); i++) {
			JSONObject element = tableau.getJSONObject(i);
			result.add(element.getString("id"));
			result.add(element.getString("mdp"));
			System.out.print("id=" + element.getString("id"));
			System.out.print(", mdp=" + element.getString("mdp"));
		}
	break;
	case("CreatePart"):
		for(int i = 0; i < tableau.length(); i++) {
			JSONObject element = tableau.getJSONObject(i);
			result.add(element.getString("namePart"));
			result.add(element.getString("purchasePrice"));
			System.out.print("id=" + element.getString("id"));
			System.out.print(", mdp=" + element.getString("mdp"));
		}
	break;
	default:
		System.out.println("Fonctionnalité non prise en charge pour l'instant");
	break;
	}	
	return result;
    }
}