package r1Serveur.Controlleurs;
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
 
public static String Identifier(String json) throws JSONException, IOException {
		
		JSONObject objet = new JSONObject(json);
		String identifier = (String) objet.get("identifier");
		
		return identifier;
	}
	
public static ArrayList<String> LectureFichier(String json) throws JSONException {
	 
	// Création d'un objet JSON
	JSONObject objet = new JSONObject(json);
	System.out.println("Afficage du JSON : ");
	System.out.println(json);
 
	// Traitement du fichier reçu
	System.out.println("\nparcours du fichier Json :");
	JSONArray tableau = objet.getJSONArray("data");
	ArrayList<String> result = new ArrayList();
	for(int i = 0; i < tableau.length(); i++) {

		result.add((String) tableau.get(i));
	}
	String identifier = (String) objet.get("identifier");
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