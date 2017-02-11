package r1Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.PrintWriter;

public class ProcessData implements Runnable{
	
	private ServerSocket serverSocket;
	private Socket socket = null;
	private Thread t1;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String identifier = null;
	ArrayList<String> data = new ArrayList();
	public ProcessData(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
	
	public void run(){
		try {
			while(true){
				System.out.println("Retrieving client socket");
				socket = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				String message_distant = in.readLine();
				try {
					System.out.println("Reading JSON Client");
					identifier = LectureJson.Identifier(message_distant);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				switch(identifier){
				case("Authentication"):
					t1 = new Thread(new Authentication(socket, message_distant, out));
					t1.start();
				break;
				case("AjoutVehicule"):
					try{
					ArrayList<String> result = LectureJson.LectureFichier(message_distant);
					CarManager.sauverEnBase(result);
					System.out.println("Véhicule ajouter dans la base");
					data.add("Véhicule ajouter dans la base");
					String JsonMessage = EcritureJson.WriteJson("QueryAjoutSuccess", data);
					System.out.println("Sending JSON to Client");
					out.println(JsonMessage);
					out.flush();
					
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
