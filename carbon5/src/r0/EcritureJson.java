import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;

public class EcritureJson {
 
    public static void main(String[] args) throws JSONException {
	// Génération du JSON depuis un tableau d'objets
	Personne p[] = { new Personne("DIALLO", "Thierno", 30),
			 new Personne("DIALLO", "Oury", 25)};
	JSONObject objet = new JSONObject();
	objet.put("contacts", new JSONArray(p));
	objet.put("tel", "01.03.00.03.03");
	System.out.println(objet);
	// Création du fichier de sortie
	FileWriter fs = null;
	try {
		fs = new FileWriter("TEST_IO.txt");
		objet.write(fs);
	    fs.flush();
	} catch(IOException e) {
	    System.err.println("Erreur lors de l'ouverture du fichier ");
	    System.err.println(e);
	    System.exit(-1);
	}
    }
}