package com.abhay.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.abhay.chatapp.dto.UserDTO;
import com.abhay.chatapp.utils.Encryption;

// USER CRUD
public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "select usersid from users where usersid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userDTO.getUserid());
			String encryptedPassword = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2, encryptedPassword);
			rs = pstmt.executeQuery();
			return rs.next();
//			if(rs.next()) {
//				return true;
//			}
//			else {
//				return false;
//			}
		}

		finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public int add(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
//		System.out.println("Record "+ userDTO.getUserid()+" "+ userDTO.getPassword());
		Connection connection = null;
		Statement stmt = null;
		try { // Guarded Region
			connection = CommonDAO.createConnection(); // STep-1 Connection created
			// Step-2 We do Query
			stmt = connection.createStatement();
			int record = stmt.executeUpdate("insert into users(usersid,password) values('" + userDTO.getUserid() + "','"
					+ Encryption.passwordEncrypt(new String(userDTO.getPassword())) + "');");
			return record;
		} finally { // Always execute (Resource clean)
			if (stmt != null) {
				stmt.close();
				connection.close();
			}
		}
	}
}
