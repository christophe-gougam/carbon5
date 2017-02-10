package r1Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.PrintWriter;

public class Serveur {
	//Initialize the socket on the server's side and listens for a user to connect
	//Mettre en place threads / m�thode pour auth / m�thode pour requ�te
	public static ServerSocket serversocket = null;
	public static Thread t;
	public static int portServer = 50000;
	
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

// lancer une classe processdata; process data lit le json et renvoie l� ou il faut