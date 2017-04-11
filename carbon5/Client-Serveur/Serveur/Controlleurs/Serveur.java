package Serveur.Controlleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

/**
 * Class Serveur 
 * @author Carbon5
 */
public class Serveur {
	//Initialize the socket on the server's side with port number
	
	public static ServerSocket serversocket = null;
	public static Thread t;
	public static int portServer = 50000;
	final static Logger logger = Logger.getLogger(Serveur.class);

	
	/**
     * Method main: Launch a processdata thread; Process data reads the json and returns where it is needed
     * @param args 
     */
	public static void main(String[] args) {
	
		try {
			
			serversocket = new ServerSocket(portServer);
			logger.info("Server is listening on port "+portServer);
			
			t = new Thread(new ProcessData(serversocket));
			t.start();
			
		} catch (IOException e) {
			logger.error("Port server "+portServer+" is already in use !");
		}
	}
}
