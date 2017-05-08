package Modele;
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EcritureJson {

    
	//changer type renvoyé, doit etre un string
    public static String WriteJson(String identifier, ArrayList<String> data){
    	String identifier2 = identifier;
    	JSONObject json = new JSONObject();
    	
    	try {
    		json.put("identifier", identifier2);
    		json.put("data", new JSONArray(data));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//convertir le json en String
    	String file = json.toString();
    	return file;
    	
    }
    
    public static String writeJson(String identifier, ArrayList<String> datatypecar, ArrayList<String> datadefect){
    	String identifier1 = identifier;
    	JSONObject json = new JSONObject();
    	
    	try {
    		json.put("identifier", identifier1);
    		json.put("data", new JSONArray(datatypecar));
			json.put("dataDefect", new JSONArray(datadefect));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//convertir le json en String
    	String file = json.toString();
    	return file;
    	
    }
}