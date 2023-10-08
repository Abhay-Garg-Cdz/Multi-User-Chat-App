package com.abhay.chatapp.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.BindException;
import java.sql.SQLException;

import javax.swing.JButton;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.abhay.chatapp.dao.UserDAO;
import com.abhay.chatapp.dto.UserDTO;
import com.abhay.chatapp.network.Server;
import com.abhay.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField useridTextField;
	private JLabel userid;
	private JLabel passwordlbl;
	private JPasswordField passwordField;
	private static Server server;

	public static void main(String[] args) throws IOException {

		new UserScreen();
		try {
		server = new Server();
		}catch(BindException ex) {
//			ex.printStackTrace();
		}finally {
			if(server!=null) {
				return;
			}
		}
		
	}

	UserDAO userDAO = new UserDAO();

	private void register() {
		String userid = useridTextField.getText();
		char[] password = passwordField.getPassword();
//		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			int result = userDAO.add(userDTO);
			if (result > 0) {
				System.out.println("Record Added...");
				JOptionPane.showMessageDialog(this, "Register Succesfully");
			} else {
				System.out.println("Record Not Added...");
				JOptionPane.showMessageDialog(this, "Register Fail");
			}
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue....");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Some Generic exception Raised....");
			ex.printStackTrace(); // Where is the exception
		}
	}

	private void doLogin() {
		String userid = useridTextField.getText();
		char[] password = passwordField.getPassword();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message = "";
			if (userDAO.isLogin(userDTO)) {
				message = "Welcome " + userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message,userid);
				dashBoard.setVisible(true);
			} else {
				message = "Invalid Userid or Password";
				JOptionPane.showMessageDialog(this, message);
			}
//			JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		getContentPane().setFont(new Font("Segoe UI Variable", Font.BOLD, 40));
		setTitle("LOGIN");
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel loginlbl = new JLabel("Login");
		loginlbl.setFont(new Font("Bradley Hand ITC", Font.BOLD, 45));
		loginlbl.setHorizontalAlignment(SwingConstants.CENTER);
		loginlbl.setBounds(215, 50, 149, 51);
		getContentPane().add(loginlbl);

		useridTextField = new JTextField();
		useridTextField.setFont(new Font("Segoe UI Light", Font.ITALIC, 20));
		useridTextField.setBounds(245, 175, 299, 39);
		getContentPane().add(useridTextField);
		useridTextField.setColumns(10);

		userid = new JLabel("User ID");
		userid.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 35));
		userid.setBounds(33, 175, 125, 39);
		getContentPane().add(userid);

		passwordlbl = new JLabel("Password");
		passwordlbl.setFont(new Font("Pristina", Font.BOLD | Font.ITALIC, 35));
		passwordlbl.setBounds(33, 281, 158, 39);
		getContentPane().add(passwordlbl);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		passwordField.setBounds(245, 281, 299, 39);
		getContentPane().add(passwordField);

		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbtn.setFont(new Font("Segoe Script", Font.BOLD, 20));
		loginbtn.setBounds(245, 352, 125, 39);
		getContentPane().add(loginbtn);

		JButton Registerbtn = new JButton("Register");
		Registerbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		Registerbtn.setFont(new Font("Segoe Script", Font.BOLD, 20));
		Registerbtn.setBounds(439, 437, 125, 39);
		getContentPane().add(Registerbtn);
		setSize(608, 524);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
