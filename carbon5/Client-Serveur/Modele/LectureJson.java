package Modele;
import org.json.JSONObject;
import org.json.JSONString;

import Serveur.Controlleurs.Serveur;

import org.apache.log4j.Logger;
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
	final static Logger logger = Logger.getLogger(Serveur.class);
	public static String Identifier(String in) throws JSONException {
		JSONObject objet = new JSONObject(in);
		String identifier = (String) objet.get("identifier");
		
		return identifier;
	}
	
    public static ArrayList<String> LectureFichier(String fs) throws JSONException {
 
    	
	// Cr�ation d'un objet JSON
	JSONObject objet = new JSONObject(fs);
	logger.info("Afficage du JSON : ");
	logger.info(fs);
 
	// Traitement du fichier re�u
	logger.info("\nparcours du fichier Json :");
	JSONArray tableau = objet.getJSONArray("data");
	ArrayList<String> result = new ArrayList<String>();
	String identifier = (String) objet.get("identifier");
	
	switch(identifier){
	
	case("Authentication"):
		
			result.add(tableau.getString(0));
			result.add(tableau.getString(1));
			System.out.print("id=" + tableau.getString(0));
			System.out.print(", mdp=" + tableau.getString(1)+"\n");

	break;
	case("CreatePart"):
	
			result.add(tableau.getString(0));
			result.add(tableau.getString(1));
			System.out.print("namePart=" + tableau.getString(0));
			System.out.print(", Price=" + tableau.getString(1)+"\n");
	break;
	case("ModificationPart"):
		
		result.add(tableau.getString(0));
		result.add(tableau.getString(1));
		System.out.print("namePart=" + tableau.getString(0));
		System.out.print("Price=" + tableau.getString(1)+"\n");
	break;
	case("DeletePart"):
		result.add(tableau.getString(0));
		System.out.print("namePart=" + tableau.getString(0)+"\n");
	break;
	case("SelectAllParts"):
		logger.info("retrieving all parts");
	break;
//<<<<<<< HEAD
        case("SelectAllParking"):
                logger.info("retrieving all parking");
        break;
//=======
//>>>>>>> origin/Develop
	case("Search"):
		logger.info("retrieving car info");
		result.add(tableau.getString(0));
		System.out.print(", PUce=" + tableau.getString(0)+"\n");
	break;
	case("LoadAllComboBox"):
		logger.info("retrieving all typeCar");
	break;
	case("addEntryStock"):	case("addOutStock"):
		result.add(tableau.getString(0));
		result.add(tableau.getString(1));
		result.add(tableau.getString(2));
		System.out.print("date=" + tableau.getString(0));
		System.out.print(", Quantite=" + tableau.getString(1)+"\n");
	break;
	case("AjoutVehicule"):
		for(int i=0; i<tableau.length(); i++)
		{
			result.add(tableau.getString(i));
		}
		System.out.print("tableau cr�� \n");
	break;
	default:
		logger.info("Fonctionnalit� non prise en charge pour l'instant");
	break;
	}	
	return result;
    }
}
