package r1Client;

import java.net.Socket;
import java.util.ArrayList;

public class ServerConnect implements Runnable{
	
	public static Socket socket = null;
	public static Thread t2;
	public static String serverAdress = "127.0.0.1";
	public static int portServer = 4000;
	public ArrayList<String> data;
	public String identifier;
	
	public ServerConnect(ArrayList<String> data, String identifier) {
	
		identifier = identifier;
		data = data;	
	}
	
	public void run(){
		try{
			socket = new Socket(serverAdress, portServer);
			t2 = new Thread(new Connection(socket, data, identifier));
		}catch (Exception e){
			System.out.println("Erreur de connexion au serveur");
		}
	}
}
