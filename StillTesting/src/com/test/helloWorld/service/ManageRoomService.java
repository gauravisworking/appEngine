package com.test.helloWorld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.helloWorld.common.vo.RoomVO;
import com.test.helloWorld.db.ConnectionUtility;

public class ManageRoomService {

	public String addRoom(RoomVO roomVO){

		String msg=null;
		String statement = null;
		Connection conn = null;
		PreparedStatement stmt = null;
        int success = 0;
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "INSERT INTO room (name,house_fk) VALUES(?,?)";
			stmt= null;
	        stmt = conn.prepareStatement(statement);
	        stmt.setString(1, roomVO.getName());
	        stmt.setLong(2, roomVO.getHouseFk());
	        success =stmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			if(conn != null)
			ConnectionUtility.closeConnection(conn);
		}
         if (success == 1) {
        	msg="Room Added Successfully";
        } else if (success == 0) {
        	msg="Error in Creating connection";
        }
		return msg;
	
		
	}
	
	public List<RoomVO> getAllRoomForHouse(Long houseFk){
		String statement = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<RoomVO> roomList = new ArrayList<RoomVO>();
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "SELECT id, name from room where house_fk = " + houseFk;
	    	stmt = conn.createStatement();
	    	rs= stmt.executeQuery(statement);
	    	 while (rs.next()) {
	    		 RoomVO room = new RoomVO();
	                room.setName(rs.getString("name"));
	                room.setId(Long.parseLong(rs.getString("id")));
	                roomList.add(room);
	            }
		} 
		catch (SQLException e) 
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			if(conn != null)
			ConnectionUtility.closeConnection(conn);
		}
		return roomList;
	
		
	}
}
