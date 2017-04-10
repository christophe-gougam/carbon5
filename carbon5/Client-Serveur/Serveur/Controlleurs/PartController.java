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
	ArrayList<String> data = new ArrayList();
	String JsonMessage;
	
	int stock = 0;
	String namePart = null;
	Float purchasePrice = null;
	
	public PartController(Socket s, String in, PrintWriter out){
		 this.socket = s;
		 this.in = in;
		 this.out=out;
	}
	
	public void run() {
		
		try{
			ArrayList<String> result = LectureJson.LectureFichier(in);
			namePart = result.get(0);
			purchasePrice = Float.parseFloat(result.get(1));
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		System.out.println("Retrieving connection from Pool");
		ConnectionPool pool = new ConnectionPool();
		con = pool.getConnectionFromPool();
		
		Part partToAdd = new Part(stock, namePart, purchasePrice);
		PartDAO test = new PartDAO(con);
		System.out.println("Putting through DAO");
		data = test.create(partToAdd);
		
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
		}
		System.out.println("Returning connection to pool");
	 	ConnectionPool.returnConnectionToPool(con);
	}
}
