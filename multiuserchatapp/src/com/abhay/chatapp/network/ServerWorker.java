package com.abhay.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Once job is created via Runnable so write the job inside a run function
//Assign the job to a Thread
public class ServerWorker extends Thread {
	private Socket clientSocket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Server server;
	private String quitMessage = "";

	public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.clientSocket = clientSocket;
		this.server = server;
		inputStream = clientSocket.getInputStream(); // Client Data Read
		outputStream = clientSocket.getOutputStream(); // Client Data Write
		System.out.println("New Client Comes");
	}

	@Override
	public void run() {
		//Read Data from the CLient and BroadCast the data to all
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
		while(true) {
			
				line = bufferedReader.readLine(); // \n
//				for(int i = line.length();i<=3;i--) {
//					quitMessage = quitMessage + line.charAt(i);
//				}
				System.out.println("Line Read... ");
				if("tiuq".equalsIgnoreCase(quitMessage)) {
					break; //Client Chat End
				}
				
				//outputStream.write(line.getBytes()); 
				// BroadCast to all
				for(ServerWorker  serverWorker : server.workers) {
					line = line + "\n";
					 serverWorker.outputStream.write(line.getBytes());
				}
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
			if(bufferedReader != null) {
				
					bufferedReader.close();
				

				
			}
			if(inputStream != null) {
				inputStream.close();
			}
			if(outputStream != null) {
				outputStream.close();
			}
			if(clientSocket != null) {
				clientSocket.close();
			}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
