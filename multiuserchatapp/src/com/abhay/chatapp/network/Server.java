package com.abhay.chatapp.network;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.abhay.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>(); // Contains all client sockets

	public Server() throws IOException,BindException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Starts and Waiting for the Client to join....");
		handleClientRequest();

	}

	// Multiple client HankShaking
	public void handleClientRequest() throws IOException {
		while (true) {
			Socket clientSocket = serverSocket.accept();// HankShaking
			// Per Client Per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket, this); // Creating a New Worker/Thread
			workers.add(serverWorker);
			serverWorker.start();
		}
	}

	/* Single ClLient */
	/*
	 * public Server() throws IOException { int PORT =
	 * Integer.parseInt(ConfigReader.getValue("PORTNO")); serverSocket = new
	 * ServerSocket(PORT);
	 * System.out.println("Server Started and waiting for Client connection...");
	 * Socket socket = serverSocket.accept(); // HandShaking
	 * 
	 * System.out.println("Client Joins the Network.....");
	 * 
	 * InputStream inputStream = socket.getInputStream(); //read Bytes from Network
	 * byte [] inputStreamArray = inputStream.readAllBytes(); String readInputStream
	 * = new String(inputStreamArray); //Bytes Convert into String
	 * System.out.println("Message received from CLient  "+ readInputStream);
	 * inputStream.close(); socket.close(); }
	 */
//	public static void main(String[] args) throws IOException {
//		new Server();
//	}

}
