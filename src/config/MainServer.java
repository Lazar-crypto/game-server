package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import services.ClientThread;

public class MainServer {

	//lista klijenata
	public static LinkedList<ClientThread> clients = new LinkedList<>();
	
	
	public static void main(String[] args) throws IOException {

		//soket za slusanje
		ServerSocket serverSocket = new ServerSocket(9999);
		
		int clientNumber = 1;
		
		while(true) {
			
			System.out.println("Waiting for connection...");
			
			Socket connectionSocket = serverSocket.accept();
			System.out.println("Client" +clientNumber+ " connected!");
			System.out.println();

			//novi tred za konektovanog klijenta
			ClientThread client = new ClientThread(connectionSocket,clientNumber);
			clientNumber ++;

			//zapamti klijenta
			clients.add(client);
			
			//pokreni tred
			client.start();
			
		}
		
	}

}
