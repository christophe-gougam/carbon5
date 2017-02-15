package r1Client;
import java.io.IOException;


import java.io.InputStream;
import java.net.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;

//import r1Serveur.ConnectionPool.java;

public class ServerConnect{
	
	private static Socket socket = null;
	private static int portServer = 50000;
	private ArrayList<String> data;
	private String identifier;
	private String serverAddress = "";	//"127.0.0.1"
	private JFrame frame=null;
	
	public ServerConnect(ArrayList<String> data, String identifier, JFrame f) {
	
		this.identifier = identifier;
		this.data = data;
		this.frame=f;

		try{
			
//			Properties prop = new Properties();
//			String portServerFile;
//			String serverAddressFile;
//			InputStream input = null;
//			String filename = "configClient.properties" ;
//			
//			input = ServerConnect.class.getClassLoader().getResourceAsStream(filename);
//			
//			if (input == null) {
//				System.out.println("Sorry, unable to find " + filename);
//			}
//			// load a properties file
//			try {
//				prop.load(input);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

//			portServer = Integer.parseInt(prop.getProperty("portServer"));
//			serverAddress = prop.getProperty("serverAddress");
			String serverAddress = InetAddress.getLocalHost().getHostAddress().toString();
			System.out.println("Ouverture de la socket avec l'adresse/port du serveur et tentative de connexion");
			socket = new Socket(serverAddress, portServer);
			new Connection(socket, data, identifier, frame);
		}catch (Exception e){
			System.out.println("Erreur de connexion au serveur");
		}
	}
}
