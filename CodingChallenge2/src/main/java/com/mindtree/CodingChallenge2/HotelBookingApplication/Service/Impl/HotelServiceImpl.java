package com.mindtree.CodingChallenge2.HotelBookingApplication.Service.Impl;

import java.sql.Connection;

import com.mindtree.CodingChallenge2.HotelBookingApplication.DAO.HotelDao;
import com.mindtree.CodingChallenge2.HotelBookingApplication.DAO.Impl.HotelDaoImpl;
import com.mindtree.CodingChallenge2.HotelBookingApplication.Service.HotelService;

public class HotelServiceImpl implements HotelService {

	HotelDao dao=new HotelDaoImpl();
	public int createHotel(Connection con) {
		return  dao.createHotel(con);
		
	}

	public void CreateRoom(int id, Connection con) {
		dao.CreateRoom(id, con);

	}

	public void hotelsInCity(String city, Connection con) {
		dao.hotelsInCity(city, con);

	}

}
