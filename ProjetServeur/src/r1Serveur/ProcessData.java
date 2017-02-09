package r1Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import org.json.JSONException;

import java.io.PrintWriter;

public class ProcessData implements Runnable{
	
	private ServerSocket serverSocket;
	private Socket socket = null;
	private Thread t1;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String identifier = null;
	public ProcessData(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
	
	public void run(){
		try {
			while(true){
				System.out.println("Récupération de la socket client");
				socket = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				 String message_distant = in.readLine();
				try {
					System.out.println("Lecture du JSON Client");
					identifier = LectureJson.Identifier(message_distant);
					System.out.println(identifier);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				switch(identifier){
				case("Authentication"):
					t1 = new Thread(new Authentication(socket, message_distant, out));
					t1.start();
					
				case("query"):
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
