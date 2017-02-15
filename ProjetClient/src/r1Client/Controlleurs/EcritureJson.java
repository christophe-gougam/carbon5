package r1Client.Controlleurs;
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