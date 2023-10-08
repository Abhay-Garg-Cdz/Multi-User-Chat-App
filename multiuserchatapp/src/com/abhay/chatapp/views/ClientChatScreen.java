package com.abhay.chatapp.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.abhay.chatapp.network.Client;
import com.abhay.chatapp.utils.UserInfo;

public class ClientChatScreen extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String userid;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;

//	public static void main(String[] args) {
//		
//					try {
//						new ClientChatScreen();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//	}

	private void sendIt() {
		String message = textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME + " - " + message);
	 	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public ClientChatScreen(String userid) throws UnknownHostException, IOException {
//		System.out.println("In the Client Chat Screen.....");
		
		setResizable(false);
		this.userid = userid;
		setTitle("Chat Window - "+this.userid);
//		System.out.println("Chat Window Title Printed...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		textArea = new JTextArea();
//		System.out.println("Text Area Created...");
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 782, 401);
		contentPane.add(scrollPane);

		textArea.setFont(new Font("Candara", Font.ITALIC, 20));
		textArea.setBounds(10, 10, 840, 489);
		scrollPane.setViewportView(textArea);

		textField = new JTextField();
		textField.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		textField.setBounds(0, 401, 648, 50);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 50));
		btnNewButton.setBounds(642, 401, 150, 50);
		contentPane.add(btnNewButton);
//		System.out.println("Before Text Area....");
		
		client = new Client(textArea);
		
		setVisible(true);

	}
}
