package r1Serveur.Controlleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.PrintWriter;

/**
 * Class Serveur 
 * @author Carbon5
 */
public class Serveur {
	//Initialize the socket on the server's side with port number
	
	public static ServerSocket serversocket = null;
	public static Thread t;
	public static int portServer = 50000;
	
	/**
     * Method main: Launch a processdata thread; Process data reads the json and returns where it is needed
     * @param args 
     */
	public static void main(String[] args) {
	
		try {
			
			serversocket = new ServerSocket(portServer);
			System.out.println("Server is listening on port "+portServer);
			
			t = new Thread(new ProcessData(serversocket));
			t.start();
			
		} catch (IOException e) {
			System.err.println("Port server "+portServer+" is already in use !");
		}
	}
}

//https://openclassrooms.com/courses/introduction-aux-sockets-1

// lancer une classe processdata; process data lit le json et renvoie là ou il faut