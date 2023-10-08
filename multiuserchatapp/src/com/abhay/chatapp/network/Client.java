package com.abhay.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.abhay.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream outputStream;
	InputStream inputStream;
	ClientWorker clientWorker;
	JTextArea textArea;

	public Client(JTextArea textArea) throws UnknownHostException, IOException {
//		System.out.println("Reached here....");
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
//		System.out.println("Parsed PORT no finely ......");
		socket = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
	

		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
		this.textArea = textArea;
		readMessages();
//		System.out.println("Client is ready.... ");
//		System.out.println("Enter the Message Send to the Server....");
//		 Scanner scanner = new Scanner(System.in);
//		 String message = scanner.nextLine();
//		 OutputStream outputStream = socket.getOutputStream(); // Write Bytes on network
//		 outputStream.write(message.getBytes());
//		 System.out.println("Message Send to the Server...");
//		 scanner.close();
//		 outputStream.close();
//		socket.close();
	}

	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		outputStream.write(message.getBytes());
	}

	public void readMessages() {
		clientWorker = new ClientWorker(inputStream, textArea); // calling a read thread
		clientWorker.start();
	}
	
}
