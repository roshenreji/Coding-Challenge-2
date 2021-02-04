package com.mindtree.CodingChallenge2.HotelBookingApplication.DAO;

import java.sql.Connection;

public interface HotelDao {

	public int createHotel(Connection con);
	public void CreateRoom(int id,Connection con);
	public void hotelsInCity(String city,Connection con);
}
