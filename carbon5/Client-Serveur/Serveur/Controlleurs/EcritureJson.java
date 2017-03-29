package Serveur.Controlleurs;
import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class Ecriture Json 
 * @author Carbon5
 */
public class EcritureJson {
 
    /**
     * Method creates Json with an identifier and an array containing data to give to the server
     * @param identifier
     * @param data
     * @return String file
     */
    public static String WriteJson(String identifier, ArrayList<String> data){
    	String identifier2 = identifier;
    	JSONObject json = new JSONObject();
    	
    	try {
    		//insert data into JSON
			json.put("data", new JSONArray(data));
			//insert identifier into JSON
			json.put("identifier", identifier2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//convert JSON into String
    	String file = json.toString();
    	return file;
    	
    }
}