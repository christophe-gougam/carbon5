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
	
    public static ArrayList<String> LectureFichier(String fs) throws JSONException {
 
    	
	// Création d'un objet JSON
	JSONObject objet = new JSONObject(fs);
	System.out.println("Afficage du JSON : ");
	System.out.println(fs);
 
	// Traitement du fichier reçu
	System.out.println("\nparcours du fichier Json :");
	JSONArray tableau = objet.getJSONArray("data");
	ArrayList<String> result = new ArrayList<String>();
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
	
			result.add(tableau.getString(0));
			result.add(tableau.getString(1));
			System.out.print("namePart=" + tableau.getString(0));
			System.out.print(", Price=" + tableau.getString(1));
	break;
	case("ModificationPart"):
		
		result.add(tableau.getString(0));
		result.add(tableau.getString(1));
		result.add(tableau.getString(2));
		System.out.print("IdPart=" + tableau.getString(0));
		System.out.print("namePart=" + tableau.getString(1));
		System.out.print(", Price=" + tableau.getString(2));
break;
	default:
		System.out.println("Fonctionnalité non prise en charge pour l'instant");
	break;
	}	
	return result;
    }
}