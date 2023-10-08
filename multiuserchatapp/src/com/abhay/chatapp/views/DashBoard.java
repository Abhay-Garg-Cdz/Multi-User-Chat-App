package com.abhay.chatapp.views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class DashBoard extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DashBoard(String message,String userid) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle(message);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Chat");
		menuBar.add(mnNewMenu);

		JMenuItem startChat = new JMenuItem("Start Chat");
		startChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					System.out.println("In the DashBoard Screen");
					setVisible(false);
					dispose();
					ClientChatScreen clientChatScreen = new ClientChatScreen(userid);
					clientChatScreen.setVisible(true);
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(startChat);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel imagelbl = new JLabel("");
		imagelbl.setBackground(new Color(255, 255, 0));
		imagelbl.setIcon(new ImageIcon(DashBoard.class
				.getResource("/images/HD-wallpaper-whatsapp-background-cool-dark-green-new-theme-whatsapp.jpg")));
		imagelbl.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(imagelbl);
	}

}
