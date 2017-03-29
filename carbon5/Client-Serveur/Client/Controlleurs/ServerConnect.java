package Client.Controlleurs;
import java.io.IOException;

import java.io.InputStream;
import java.net.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * Class ServerConnect creating connection to server
 * @author Carbon5
 */
public class ServerConnect{
	
	public static Socket socket = null;
	public static Thread t2;
	public static int portServer = 50000;
	public ArrayList<String> data;
	public String identifier;
	public String serverAddress = "";	//"127.0.0.1"
	JFrame frame=null;
	
	/**
     * Class constructor
     * @param data
     * @param identifier
     * @param f 
     */
	public ServerConnect(ArrayList<String> data, String identifier, JFrame f) {
	
		this.identifier = identifier;
		this.data = data;
		this.frame=f;

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
			System.out.println("Ouverture de la socket avec l'adresse/port du serveur et tentative de connexion");
			socket = new Socket(serverAddress, portServer);
			new Connection(socket, data, identifier, frame);
		}catch (Exception e){
			System.out.println("Erreur de connexion au serveur");
		}
	}
}
