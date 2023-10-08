package com.abhay.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

//Client Data Read
public class ClientWorker extends Thread {
	private InputStream inputStream;
	private JTextArea textArea;

	public ClientWorker(InputStream inputStream, JTextArea textArea) {
		this.inputStream = inputStream;
		this.textArea = textArea;
	}

	@Override
	public void run() {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while (true) {
				line = bufferReader.readLine(); // \n

				textArea.setText(textArea.getText() + line + "\n");

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
		}
	}
}
