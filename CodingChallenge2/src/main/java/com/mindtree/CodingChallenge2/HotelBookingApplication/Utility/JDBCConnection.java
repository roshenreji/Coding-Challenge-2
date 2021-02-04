package com.mindtree.CodingChallenge2.HotelBookingApplication.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
	private static final String URL="jdbc:mysql://localhost:3306/newdatabase";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Helloworld1234";

	public static Connection con=null;
	
	public static Connection getConnection() {
		
		try {
			con=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return con;
	}
	
	
	public void closeConnection(Connection con) {
		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
	
}
