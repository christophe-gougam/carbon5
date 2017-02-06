package R1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.PrintWriter;

public class Serveur {
	//Initialize the socket on the server's side and listens for a user to connect
	//Mettre en place threads / méthode pour auth / méthode pour requête
	public static ServerSocket serversocket = null;
	public static Thread t;
	public static int portServer = 4000;
	
	public static void main(String[] args) {
	
		try {
			serversocket = new ServerSocket(portServer);
			System.out.println("Le serveur est à l'écoute du port "+portServer);
			
			t = new Thread(new ProcessData(serversocket));
			t.start();
			
		} catch (IOException e) {
			System.err.println("Le port "+portServer+" est déjà utilisé !");
		}
	}
}

//https://openclassrooms.com/courses/introduction-aux-sockets-1

// lancer une classe processdata; process data lit le json et renvoie là ou il faut