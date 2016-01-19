package com.test.helloWorld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.test.helloWorld.common.vo.BoardVO;
import com.test.helloWorld.common.vo.DeviceTypeVO;
import com.test.helloWorld.db.ConnectionUtility;

public class ManageBoardService {

	public String addBoard(BoardVO boardVO){

		String msg=null;
		String statement = null;
		Connection conn = null;
		PreparedStatement stmt = null;
        int success = 0;
        ResultSet rs = null;
        Long boardId =  null;
        DeviceTypeVO deviceType = new DeviceTypeVO();
       
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "INSERT INTO board (name,room_fk,device_type_fk) VALUES(?,?,?)";
			stmt= null;
	        stmt = conn.prepareStatement(statement);
	        stmt.setString(1, boardVO.getName());
	        stmt.setLong(2, boardVO.getRoomFk());
	        stmt.setLong(3, boardVO.getDeviceTypeFk());
	        success =stmt.executeUpdate();
	        
	        statement = "select max(id) as id from board";
	        stmt = conn.prepareStatement(statement);
	        rs = stmt.executeQuery();
	        while(rs.next())
	        boardId = Long.parseLong(rs.getString("id"));
	        
	        statement = "SELECT id ,name as name, digital_pins as digitalPins ,analog_pins as analogPins ,pwm_pins as pwmPins FROM device_type where id = "+boardVO.getDeviceTypeFk();
	        stmt = conn.prepareStatement(statement);
	    	//stmt.setLong(1, boardVO.getDeviceTypeFk());
	    	rs= (ResultSet) stmt.executeQuery(statement);
	    	while (rs.next()) {
	               
	                deviceType.setId(rs.getLong("id"));
	                deviceType.setName(rs.getString("name"));
	                deviceType.setDigitalPins(rs.getString("digitalPins"));
	                deviceType.setAnalogPins(rs.getString("analogPins"));
	                deviceType.setPwmPins(rs.getString("pwmPins"));
	           }
	    	
	    	
	    	List<String> digitalPinList = Arrays.asList(deviceType.getDigitalPins().split("\\s*,\\s*"));
	    	List<String> analogPinList = Arrays.asList(deviceType.getAnalogPins().split("\\s*,\\s*"));
	    	List<String> pwnMinList = Arrays.asList(deviceType.getPwmPins().split("\\s*,\\s*"));
	    	 
	    	for (String digitalPin : digitalPinList)
	    	{
	    		
	    		statement = "INSERT INTO point_pin_action (pin_number,pin_type ,device_type_fk,board_fk) VALUES(?,?,?,?)";
				stmt= null;
		        stmt = conn.prepareStatement(statement);
		        stmt.setString(1, digitalPin);
		        stmt.setString(2, "DIGITAL");
		        stmt.setLong(3,deviceType.getId());
		        stmt.setLong(4, boardId);
		        success =stmt.executeUpdate();
	    	}
	    	
	    	for (String analogPin : analogPinList)
	    	{
	    		
	    		statement = "INSERT INTO point_pin_action (pin_number,pin_type ,device_type_fk,board_fk) VALUES(?,?,?,?)";
				stmt= null;
		        stmt = conn.prepareStatement(statement);
		        stmt.setString(1, analogPin);
		        stmt.setString(2, "ANALOG");
		        stmt.setLong(3,deviceType.getId());
		        stmt.setLong(4, boardId);
		        success =stmt.executeUpdate();
	    	}
	    	
	    	for (String pwmPin : pwnMinList)
	    	{
	    		
	    		statement = "INSERT INTO point_pin_action (pin_number,pin_type ,device_type_fk,board_fk) VALUES(?,?,?,?)";
				stmt= null;
		        stmt = conn.prepareStatement(statement);
		        stmt.setString(1, pwmPin);
		        stmt.setString(2, "PWM");
		        stmt.setLong(3,deviceType.getId());
		        stmt.setLong(4, boardId);
		        success =stmt.executeUpdate();
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
         if (success == 1) {
        	msg="Board Added Successfully";
        } else if (success == 0) {
        	msg="Error in Creating connection";
        }
		return msg;
	
		
	}
	
	public List<BoardVO> getAllBoardForRoom(Long roomFk){
		String statement = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "SELECT id, name from board where room_fk = " + roomFk;
	    	stmt = conn.createStatement();
	    	rs= stmt.executeQuery(statement);
	    	 while (rs.next()) {
	    		 BoardVO board = new BoardVO();
	                board.setName(rs.getString("name"));
	                board.setId(Long.parseLong(rs.getString("id")));
	                boardList.add(board);
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
		return boardList;
	
		
	}
}
