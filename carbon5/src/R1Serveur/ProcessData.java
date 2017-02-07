package R1Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
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
		serverSocket = serverSocket;
	}
	
	public void run(){
		try {
			while(true){
				socket = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				try {
					String identifier = LectureJson.Identifier(in);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				switch(identifier){
				case("authentication"):
					t1 = new Thread(new Authentication(socket, in));
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
