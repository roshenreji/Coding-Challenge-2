package com.mindtree.CodingChallenge2.HotelBookingApplication.Service;

import java.sql.Connection;

public interface HotelService {

	public int createHotel(Connection con);
	public void CreateRoom(int id,Connection con);
	public void hotelsInCity(String city,Connection con);
}
