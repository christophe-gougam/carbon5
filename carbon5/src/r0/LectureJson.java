import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
 
public class LectureJson {
 
    public static void main(String[] args) throws JSONException {
	// Ouverture du fichier
	FileInputStream fs = null;
	try {
	    fs = new FileInputStream("TEST_IO.txt");
	} catch(FileNotFoundException e) {
	    System.err.println("Fichier introuvable");
	    System.exit(-1);
	}
 
	// Récupération de la chaîne JSON depuis le fichier
	String json = new String();
	Scanner scanner = new Scanner(fs);
	while(scanner.hasNext())
	    json += scanner.nextLine();
	scanner.close();
 
	// Création d'un objet JSON
	JSONObject objet = new JSONObject(json);
	System.out.println("Afficage du JSON : ");
	System.out.println(json);
 
	// Affichage à l'écran
	System.out.println("\nparcours du fichier Json :");
	String tel= (String) objet.get("tel");
	System.out.println("tel=" + tel);
	JSONArray tableau = objet.getJSONArray("contacts");
	for(int i = 0; i < tableau.length(); i++) {
	    JSONObject element = tableau.getJSONObject(i);
	    System.out.print("nom=" + element.getString("nom"));
	    System.out.print(", prenom=" + element.getString("prenom"));
	    System.out.println(", age=" + element.getInt("age"));
	}
    }
}