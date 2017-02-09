package r1Client;
import java.net.*;

import java.net.Socket;
import java.util.ArrayList;

public class ServerConnect implements Runnable{
	
	public static Socket socket = null;
	public static Thread t2;
	public static int portServer = 50000;
	public ArrayList<String> data;
	public String identifier;
	
	public ServerConnect(ArrayList<String> data, String identifier) {
	
		this.identifier = identifier;
		this.data = data;	
	}
	
	
	public void run(){
		try{
			String serverAdress = InetAddress.getLocalHost().getHostAddress().toString();
			System.out.println("Ouverture de la socket avec l'adresse/port du serveur et tentative de connexion");
			socket = new Socket(serverAdress, portServer);
			t2 = new Thread(new Connection(socket, data, identifier));
			t2.start();
		}catch (Exception e){
			System.out.println("Erreur de connexion au serveur");
		}
	}
}
