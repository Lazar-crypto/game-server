package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import config.MainServer;

public class ClientThread extends Thread
{

	Socket connectionSocket;
	BufferedReader clientOutput;
	PrintStream serverInput;
	int clientNumber;
	
	public ClientThread(Socket connectionSocket,int clientNumber)
	{
		this.connectionSocket = connectionSocket; 
		this.clientNumber = clientNumber;
		
	}
	
	@Override
	public void run() 
	{
		try
		{
			initializeIO();
			connectionLogic();
			gamePLayLogic();
		} 
		catch (IOException e) 
		{
			System.err.println("ClientThred run() - error!");
			e.printStackTrace();
		}
	}

	private void initializeIO() throws IOException 
	{
		clientOutput = new BufferedReader(
		        new InputStreamReader(
		        		connectionSocket.getInputStream()));
	
		serverInput = new PrintStream(connectionSocket.getOutputStream());
	}

	private void connectionLogic()
	{
		//player name...
	}

	private void gamePLayLogic() throws IOException 
	{
	    String text;
	    while (true) 
	    {
	    	text = clientOutput.readLine();
	    	
	    	
	    	for (ClientThread client : MainServer.clients)
	    	{
				if(client != this) 
					client.serverInput.println(text);
			}
	   
	    }
	
	
	}
}
