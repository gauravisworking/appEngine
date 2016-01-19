package com.test.helloWorld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.BoardVO;
import com.test.helloWorld.common.vo.HouseVO;
import com.test.helloWorld.common.vo.PointPinActionVO;
import com.test.helloWorld.common.vo.PointVO;
import com.test.helloWorld.common.vo.RoomVO;
import com.test.helloWorld.common.vo.UserVO;
import com.test.helloWorld.db.ConnectionUtility;

public class ManageHouseService {

	public List<HouseVO> getAllHouses() {
		List<HouseVO> houseList =  new ArrayList<HouseVO>();
		
		Connection conn = null;
		String statement;
		Statement stmt = null;
		ResultSet rs = null;
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "SELECT id , name FROM house ";
	    	stmt = conn.createStatement();
	    	rs= (ResultSet) stmt.executeQuery(statement);
	    	 while (rs.next()) {
	                HouseVO house = new HouseVO();
	                house.setName(rs.getString("name"));
	                house.setId(rs.getString("id"));
	                house.setLeaf(true);
	                houseList.add(house);
	            }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
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
		return houseList;
	}

	public String addHouse(HouseVO houseVO) {
		String msg="Something went worng";
		String statement = null;
		Connection conn = null;
		PreparedStatement stmt = null;
        int success = 0;
        List<UserVO> userList = new ArrayList<UserVO>();
        ResultSet rs = null;
        Long houseId = null;
		try {
	    	conn = ConnectionUtility.getConnection();
	    	statement = "INSERT INTO house (name,address_1,address_2,city,state,phone_number)values(?,?,?,?,?,?)";
	    	stmt = conn.prepareStatement(statement);
	        stmt.setString(1, houseVO.getName());
	        stmt.setString(2, houseVO.getAddress1());
	        stmt.setString(3, houseVO.getAddress2());
	        stmt.setString(4, houseVO.getCity());
	        stmt.setString(5, houseVO.getState());
	        stmt.setString(6, houseVO.getPhoneNumber());
	        success = stmt.executeUpdate();
	        
	        statement = "select max(id) as id from house";
	        stmt = conn.prepareStatement(statement);
	        rs = stmt.executeQuery();
	        while(rs.next())
	        houseId = Long.parseLong(rs.getString("id"));
	        
	        userList = houseVO.getUserList();
	        for(UserVO user : userList)
	        {
	        	statement = "INSERT INTO user (first_name, last_name,company,user_name,password,email,mobile_number,house_fk) VALUES(?,?,?,?,?,?,?,?)";
				stmt= null;
		        stmt = conn.prepareStatement(statement);
		        stmt.setString(1, user.getFirstName());
		        stmt.setString(2, user.getLastName());
		        stmt.setString(3, user.getCompany());
		        stmt.setString(4, user.getFirstName());
		        stmt.setString(5, "password");
		        stmt.setString(6, user.getEmail());
		        stmt.setString(7, user.getMobileNumber());
		        stmt.setLong(8, houseId);
		        success = success + stmt.executeUpdate();
	        }
		        
			msg = "House Added successfully";
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
       
		return msg;
	}

	public String publishHouseJson(Long houseFk) {

		String statement = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		List<RoomVO> roomList = new ArrayList<RoomVO>();
		
		Gson gson = new Gson();
		String msg = "Error";
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "SELECT id, name from room where house_fk = " + houseFk;
	    	stmt = conn.createStatement();
	    	rs= stmt.executeQuery(statement);
	    	 while (rs.next()) {
	    		 RoomVO room = new RoomVO();
	    		 	room.setId(rs.getLong("id"));
	                room.setName(rs.getString("name"));
	               
	                
	                statement = "SELECT id, name, device_type_fk , device_fk from board where room_fk = " + rs.getLong("id");
	    	    	stmt = conn.createStatement();
	    	    	rs1= stmt.executeQuery(statement);
	    	    	List<BoardVO> boardList = new ArrayList<>();
	    	    	while (rs1.next()) {
	    	    		 BoardVO board = new BoardVO();
	    	    		 board.setId(rs1.getLong("id"));
	    	    		 board.setName(rs1.getString("name"));
	    	    		 board.setDeviceTypeFk(rs1.getLong("device_type_fk"));
	    	    		 board.setDeviceFk(rs1.getLong("device_fk"));
	    	    		 
	    	    		 statement =  "SELECT id, name, point_type_fk from point where board_fk = " + board.getId();
	 	    	    	 stmt = conn.createStatement();
	 	    	    	 rs2= stmt.executeQuery(statement);
	 	    	    	 List<PointVO> pointList = new ArrayList<PointVO>();
	 	    	    	 while (rs2.next()) {
		 	   	    		 PointVO point = new PointVO();
		 	   	    		 point.setName(rs2.getString("name"));
		 	   	    		 point.setId(rs2.getLong("id"));
		 	   	    		 point.setBoardFk(board.getId());
		 	   	    		 point.setPointTypeFk(rs2.getLong("point_type_fk"));
			 	   	    	
		 	   	    		 statement =  "SELECT ppa.id,ppa.perform,ppa.value,ppa.pin_number,ppa.pin_type,ppa.device_fk,ppa.device_type_fk,ppa.action_fk, action.name as action_name FROM point_pin_action as ppa join actoin as action ON ppa.action_fk = action.id where point_fk =" + point.getId();
		 	    	    	 stmt = conn.createStatement();
		 	    	    	 rs3= stmt.executeQuery(statement);
		 	    	    	 List<PointPinActionVO> pointPinActionList = new ArrayList<>();
		 	    	    	 while(rs3.next())
		 	    	    	 {
		 	    	    		PointPinActionVO pointPinAction = new PointPinActionVO();
		 	    	    		pointPinAction.setId(rs3.getLong("id"));
		 	    	    		pointPinAction.setPointFk(point.getId());
		 	    	    		pointPinAction.setPerform(rs3.getString("perform"));
		 	    	    		pointPinAction.setValue(rs3.getString("value"));
		 	    	    		pointPinAction.setPinNumber(rs3.getString("pin_number"));
		 	    	    		pointPinAction.setDeviceTypeFk(rs3.getLong("device_type_fk"));
		 	    	    		pointPinAction.setDeviceFk(rs3.getLong("device_fk"));
		 	    	    		pointPinAction.setActionFk(rs3.getLong("action_fk"));
		 	    	    		pointPinAction.setBoardFk(board.getId());
		 	    	    		pointPinAction.setActionName(rs3.getString("action_name"));
		 	    	    		pointPinActionList.add(pointPinAction);
		 	    	    	 }
		 	   	    		 point.setPointPinActionList(pointPinActionList);
		 	   	    		 pointList.add(point);
		 	   	           }
	    	    		 board.setPointList(pointList);
	    	    		 boardList.add(board);
	    	    	}
	    	    	room.setBoardList(boardList);
	    	    	roomList.add(room);
	            }
	    	 
	    	 
	    	 
	    	 statement = "UPDATE house set house_json = '"+gson.toJson(roomList)+"' where id ="+ houseFk;
	    	 stmt = conn.createStatement();
		     stmt.executeUpdate(statement);
	    	 msg = "Data Published successfully";
	    	 
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

		return msg;
	}

}
