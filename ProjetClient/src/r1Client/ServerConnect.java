package r1Client;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import r1Serveur.ConnectionPool;

public class ServerConnect implements Runnable{
	
	public static Socket socket = null;
	public static Thread t2;
	public static int portServer = 50000;
	public ArrayList<String> data;
	public String identifier;
	public String serverAddress = "10.10.10.210";
	
	public ServerConnect(ArrayList<String> data, String identifier) {
	
		this.identifier = identifier;
		this.data = data;	
	}
	
	
	public void run(){
		try{
			
			Properties prop = new Properties();
			String portServerFile;
			String serverAddressFile;
			InputStream input = null;
			String filename = "configClient.properties" ;
			
			input = ServerConnect.class.getClassLoader().getResourceAsStream(filename);
			
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
			}
			// load a properties file
			try {
				prop.load(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			portServer = Integer.parseInt(prop.getProperty("portServer"));
			serverAddress = prop.getProperty("serverAddress");
			//String serverAdress = InetAddress.getLocalHost().getHostAddress().toString();
			System.out.println("Ouverture de la socket avec l'adresse/port du serveur et tentative de connexion");
			socket = new Socket(serverAddress, portServer);
			t2 = new Thread(new Connection(socket, data, identifier));
			t2.start();
		}catch (Exception e){
			System.out.println("Erreur de connexion au serveur");
		}
	}
}
