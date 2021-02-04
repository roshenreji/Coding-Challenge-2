package com.mindtree.CodingChallenge2.HotelBookingApplication.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mindtree.CodingChallenge2.HotelBookingApplication.DAO.HotelDao;
import com.mindtree.CodingChallenge2.HotelBookingApplication.Exceptions.CityException;
import com.mindtree.CodingChallenge2.HotelBookingApplication.Exceptions.IdAbsentException;
import com.mindtree.CodingChallenge2.HotelBookingApplication.Exceptions.IdPresentException;

public class HotelDaoImpl implements HotelDao {

	Scanner sc = new Scanner(System.in);

	public void checkIdPresence(int id, Connection con) throws IdPresentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from Hotel Where Id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;
				}
			}

			if (valid == true) {
				throw new IdPresentException("Already Present");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void checkIdAbsence(int id, Connection con) throws IdAbsentException {
		int count = 0;
		boolean valid = false;
		try {

			String query = "Select id from Hotel Where Id=" + id;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;

				}
			}

			if (valid == false) {
				throw new IdAbsentException("Entry Not Found");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void checkCityAbsence(String city, Connection con) throws CityException {
		String cityName = "";
		boolean valid = false;
		try {

			String query = "Select city from Hotel Where City='" + city+"'";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				cityName = resultSet.getString(1);
				if (cityName.equals(city)) {
					valid = true;
					break;

				}
			}

			if (valid == false) {
				throw new IdAbsentException("No such city are there");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public String validateRoomType(String type) {
		boolean isValid = true;
		while (isValid) {
			if (type.equalsIgnoreCase("Luxury") || type.equalsIgnoreCase("semiLuxury")
					|| type.equalsIgnoreCase("deluxe")) {
				isValid = false;
			} else {
				System.out.println("Enter the Room Type Propely(Luxury/semiLuxury/deluxe)");
				type = sc.next();
			}
		}

		return type;
	}

	public int createHotel(Connection con) {
		String query = "Insert into Hotel values(?,?,?)";
		int id = 0;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("Enter Hotel  id: ");
			id = sc.nextInt();
			try {
				checkIdPresence(id, con);
				System.out.println("Enter Hotel Name: ");
				String hotelName = sc.next();
				System.out.println("Enter Hotel City");
				String hotelCity = sc.next();

				ps.setInt(1, id);
				ps.setString(2, hotelName);
				ps.setString(3, hotelCity);

				ps.executeUpdate();

			} catch (IdPresentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return id;

	}

	public void CreateRoom(int id, Connection con) {
		String query = "Insert into Room values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("Enter Room  Number: ");
			int roomNumber = sc.nextInt();
			System.out.println("Enter Room Type(Luxury/semiLuxury/deluxe): ");
			String roomType = sc.next();
			roomType = validateRoomType(roomType);
			System.out.println("Enter Room Cost");
			double roomCost = sc.nextDouble();

			ps.setInt(1, id);
			ps.setInt(2, roomNumber);
			ps.setString(3, roomType);
			ps.setDouble(4, roomCost);

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void hotelsInCity(String city, Connection con) {
		String cityName = "";
		try {
			checkCityAbsence(city, con);
			try {

				String query = "Select city from Hotel where city='"+city+"'";
				PreparedStatement ps = con.prepareStatement(query);

				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					cityName = resultSet.getString(1);
					if (cityName.equals(city)) {
						getHotelInfo(city, con);

					}
				}

				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		} catch (CityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void getHotelInfo(String city, Connection con) {

		int count = 1;

		String query = "Select Hotel.id,Hotel.name,Hotel.city,Room.roomNumber,Room.roomType,Room.cost from Hotel Inner Join Room on Hotel.id=Room.id where hotel.city='"
				+ city+"'";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println();
				System.out.println(count + "\t Hotel Id: " + rs.getInt(1));
				System.out.println("\t Hotel Name: " + rs.getString(2));
				System.out.println("\t City: " + rs.getString(3));
				System.out.println("\t Room Number: " + rs.getInt(4));
				System.out.println("\t Room Type: " + rs.getString(5));
				System.out.println("\t Room Cost: " + rs.getDouble(6));

				count++;
			}

			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
