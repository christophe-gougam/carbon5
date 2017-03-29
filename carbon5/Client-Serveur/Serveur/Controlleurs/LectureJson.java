package Serveur.Controlleurs;
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

/**
 * Class LectureJson read Json
 * @author Carbon5
 */
public class LectureJson {

	/**
	 * Method get identifier, returns the identifier to see what action needs to be performed 
	 * @param json
	 * @return String identifier
	 * @throws JSONException
	 * @throws IOException 
	 */	
public static String Identifier(String json) throws JSONException, IOException {
		
		JSONObject objet = new JSONObject(json);
		//get the identifier from JSON
		String identifier = (String) objet.get("identifier");
		
		return identifier;
	}
	
/**
 * Method read file, returns the data from the JSON to be used
 * @param json
 * @return result array
 * @throws JSONException 
 */
public static ArrayList<String> LectureFichier(String json) throws JSONException {
	 
	// Create JSON object
	JSONObject objet = new JSONObject(json);
	System.out.println("Afficage du JSON : ");
	System.out.println(json);
 
	//Treatment of received file
	System.out.println("\nparcours du fichier Json :");
	JSONArray tableau = objet.getJSONArray("data");
	ArrayList<String> result = new ArrayList();
	//retrieve String from JSONArray into String
	for(int i = 0; i < tableau.length(); i++) {

		result.add((String) tableau.get(i));
	}
	//retrieve identifier from JSON
	String identifier = (String) objet.get("identifier");
	//check the identifier to see what to do
	switch(identifier){
	case("Authentication"):
		System.out.print("login=" + result.get(0));
		System.out.println(", mdp=" + result.get(1));
	break;
	case("AjoutVehicule"):
		System.out.print("ajout Véhicule=" + result.get(0)+"\n");
	break;
	default :
		System.out.println("erreur default");
	}	
	return result;
    }
}