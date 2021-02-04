package com.mindtree.CodingChallenge2.HotelBookingApplication.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.mindtree.CodingChallenge2.HotelBookingApplication.Service.HotelService;
import com.mindtree.CodingChallenge2.HotelBookingApplication.Service.Impl.HotelServiceImpl;
import com.mindtree.CodingChallenge2.HotelBookingApplication.Utility.JDBCConnection;

public class HotelApplication {

	Scanner sc = new Scanner(System.in);

	public void displayMessages() {
		System.out.println();
		System.out.println("Welcome to Online Hotel Booking Site");
		System.out.println("====================");
		System.out.println("These are the choices");
		System.out.println("1. Create Hotel");
		System.out.println("2. Create Room");
		System.out.println("3. Display hotel info in a given city");
		System.out.println("4. Exit");
		System.out.println("Enter your choice: ");
	}

	public static void main(String[] args) {
		boolean isValid = true;
		int firstTime = 0, id = 0;

		HotelService service = new HotelServiceImpl();
		JDBCConnection connect = new JDBCConnection();
		HotelApplication obj = new HotelApplication();
		Connection con = connect.getConnection();

		do {
			obj.displayMessages();
			int choice = obj.sc.nextInt();
			switch (choice) {
			case 1:

				id = service.createHotel(con);
				firstTime++;
				break;
			case 2:
				if (firstTime != 0) {
					service.CreateRoom(id, con);
				}
				break;
			case 3:
				System.out.println("Enter the city whose list of Hotels you want to see");
				String city = obj.sc.next();
				service.hotelsInCity(city, con);
				break;
			case 4:
				isValid=false;
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
				default:
					System.out.println("Invalid Option, Please try again");
			}
		} while (isValid);

	}

}
