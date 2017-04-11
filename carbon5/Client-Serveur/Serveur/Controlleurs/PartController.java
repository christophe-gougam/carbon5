package Serveur.Controlleurs;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.json.JSONObject;
import org.json.JSONException;

import Modele.LectureJson;
import Modele.EcritureJson;
import Modele.Part;
import Modele.PartDAO;

import java.io.PrintWriter;
public class PartController implements Runnable{
	
	private Socket socket = null;
	String in;
	private PrintWriter out = null;
	public Thread t2;
	Connection con=null;
	ArrayList<String> data = new ArrayList<String>();
	String JsonMessage;
	
	String IdPart = null;
	String namePart = null;
	Float purchasePrice = null;
	boolean ret;
	public PartController(Socket s, String in, PrintWriter out){
		 this.socket = s;
		 this.in = in;
		 this.out=out;
	}
	
	public void run() {
		
		System.out.println("Retrieving connection from Pool");
		ConnectionPool pool = new ConnectionPool();
		con = pool.getConnectionFromPool();
		PartDAO test = new PartDAO(con);
		try{
			String identifier = LectureJson.Identifier(in);
			ArrayList<String> result = LectureJson.LectureFichier(in);
			switch(identifier){
			case("CreatePart"):
			
				
	    		namePart = result.get(0);
	    		purchasePrice = Float.parseFloat(result.get(1));
	    		Part res=test.find(namePart);
	    		int stock=res.getStock();
	    		int stocknew=stock+1;
				//vérifier si la pièce existe, incrémenter le stock
	    		
	    		//////@Thierno Problème de if à ce niveau pensez à declarer comme clé primaire le nom de la pièce dans la base///////////////
	    		
	    		if (res.getPurchasePrice()==purchasePrice){
					Part partUpdate = new Part(stocknew, namePart, purchasePrice);
					System.out.println("Updating through DAO");
					ret=test.update(partUpdate);
					if (ret)
						data.add("CreatePartOK");
					else
						data.add("CreatePartKO");
				}
				//sinon ajouter la pièce
				else{
					Part partToAdd = new Part(1, namePart, purchasePrice);
					System.out.println("Putting through DAO");
					data = test.create(partToAdd);
				}
			break;
			case("ModificationPart"):
				IdPart=result.get(0);
				namePart = result.get(1);
				purchasePrice = Float.parseFloat(result.get(2));
				Part partUpdate = new Part(IdPart, namePart, purchasePrice);
				System.out.println("Updating through DAO");
				ret=test.update(partUpdate);
				if (ret)
					data.add("ModificationPartOK");
				else
					data.add("ModificationPartKO");
			break;
			}
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		switch(data.get(0)){
		case("CreatePartOK"):
			JsonMessage = EcritureJson.WriteJson("CreatePartOK", data);
			System.out.println("Sending JSON succès to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("CreatePartKO"):
			JsonMessage = EcritureJson.WriteJson("CreatePartKO", data);
			System.out.println("Erreur lors de l'ajout de Part");
			out.println(JsonMessage);
			out.flush();
		break;
		case("ModificationPartOK"):
			JsonMessage = EcritureJson.WriteJson("ModificationPartOK", data);
			System.out.println("Sending JSON succès to Client");
			out.println(JsonMessage);
			out.flush();
		break;
		case("ModificationPartKO"):
			JsonMessage = EcritureJson.WriteJson("ModificationPartKO", data);
			System.out.println("Erreur lors de la mise à jour de cette pièce");
			out.println(JsonMessage);
			out.flush();
		}
		System.out.println("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}
