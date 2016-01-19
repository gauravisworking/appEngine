package com.test.helloWorld.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.helloWorld.common.vo.ActionVO;
import com.test.helloWorld.common.vo.DeviceTypeVO;
import com.test.helloWorld.common.vo.PointPinActionVO;
import com.test.helloWorld.common.vo.PointTypeVO;
import com.test.helloWorld.common.vo.PointVO;
import com.test.helloWorld.db.ConnectionUtility;

public class ManagePointService {
	
	public List<ActionVO> getActionsForPointType (PointVO pointVO){

		String statement = null;
		Connection conn = null;
		PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        List<ActionVO> actionList = new ArrayList<ActionVO>();
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement="SELECT action.id, action.name ,action.pin_type FROM actoin as action " 
	        		+"join action_to_point_type_map as apt ON apt.action_fk = action.id "
	        		+"join point_type as pt ON pt.id = apt.point_type_fk " 
	        		+"and pt.id = "+pointVO.getPointTypeFk();
	        stmt = conn.prepareStatement(statement);
	         rs= (ResultSet) stmt.executeQuery(statement);
	    	 while (rs.next()) {
	               ActionVO action = new ActionVO();
	               action.setId(rs.getLong("id"));
	               action.setName(rs.getString("name"));
	               action.setPinType(rs.getString("pin_type"));
	               List<PointPinActionVO> pointPinActionList = new ArrayList<PointPinActionVO>();
	               statement = "select id , pin_number from point_pin_action where board_fk ="+pointVO.getBoardFk()+" and pin_type = '"+rs.getString("pin_type")+"'";
	               stmt = conn.prepareStatement(statement);
	               rs1= (ResultSet) stmt.executeQuery(statement);
		  	    	 while (rs1.next()) {
		  	    		PointPinActionVO pointPinAction = new PointPinActionVO();
		  	    		pointPinAction.setId(rs1.getLong("id"));
		  	    		pointPinAction.setPinNumber(rs1.getString("pin_number"));
		  	    		pointPinActionList.add(pointPinAction);
		  	    	 }
	               action.setPointPinActionList(pointPinActionList);
	               actionList.add(action);
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

		return actionList;
		
	}

	public String addPoint(PointVO pointVO){

		String msg=null;
		String statement = null;
		Connection conn = null;
		PreparedStatement stmt = null;
        int success = 0;
        Long pointId = null;
        ResultSet rs = null;
        String perform = null;
		try {
	    	conn = ConnectionUtility.getConnection();
	    	statement = "INSERT INTO point (name,board_fk,point_type_fk) VALUES(?,?,?)";
			stmt= null;
	        stmt = conn.prepareStatement(statement);
	        stmt.setString(1, pointVO.getName());
	        stmt.setLong(2, pointVO.getBoardFk());
	        stmt.setLong(3, pointVO.getPointTypeFk());
	        success =stmt.executeUpdate();
	        
	        statement = "select max(id) as id from point";
	        stmt = conn.prepareStatement(statement);
	        rs = stmt.executeQuery();
	        while(rs.next())
	        pointId = Long.parseLong(rs.getString("id"));
	        
	        for(PointPinActionVO pointPinAction : pointVO.getPointPinActionList())
	        {
	        	
	        	 statement = " select room.name as roomName, board.name as boardName, point.name as pointName, action.name as actionName from room as room "
	        		        +"join board as board ON board.room_fk = room.id " 
	        		        +"join point as point ON point.board_fk = board.id "
	        		        +"join actoin as action ON action.id = " + pointPinAction.getActionFk()
	        		        +" and point.id =" + pointId;
		    	stmt = conn.prepareStatement(statement);
		    	rs= stmt.executeQuery();
		    	
		    	 while (rs.next()) {
		    		perform = rs.getString("roomName")+"_"+rs.getString("boardName")+"_"+rs.getString("pointName")+"_"+rs.getString("actionName");
		            }
	        	
	        	
	        	statement = "UPDATE point_pin_action set point_fk = "+pointId+", perform = '"+ perform+"' , action_fk = "+pointPinAction.getActionFk()+" where id="+pointPinAction.getId();
		        stmt = conn.prepareStatement(statement);
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
        	msg="Point Added Successfully";
        } else if (success == 0) {
        	msg="Error in Creating connection";
        }
		return msg;
		
	}
	
	public List<PointVO> getAllPointForBoard(Long boardFk){
		String statement = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<PointVO> pointList = new ArrayList<PointVO>();
		try {
	    	conn = ConnectionUtility.getConnection();
	    	statement = "SELECT id, name from point where board_fk = " + boardFk;
	    	stmt = conn.createStatement();
	    	rs= stmt.executeQuery(statement);
	    	 while (rs.next()) {
	    		 PointVO point = new PointVO();
	    		 point.setName(rs.getString("name"));
	    		 point.setId(Long.parseLong(rs.getString("id")));
	    		 pointList.add(point);
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
		return pointList;
	
		
	}

	public List<PointTypeVO> getAllPointType() {
		Connection conn = null;
		String statement;
		Statement stmt = null;
		ResultSet rs = null;
		List<PointTypeVO> pointTypeList = new ArrayList<PointTypeVO>();
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "SELECT id ,name as name FROM point_type ";
	    	stmt = conn.createStatement();
	    	rs= (ResultSet) stmt.executeQuery(statement);
	    	 while (rs.next()) {
	    		 	PointTypeVO pointType = new PointTypeVO();
	    		 	pointType.setId(rs.getLong("id"));
	    		 	pointType.setName(rs.getString("name"));
	                pointTypeList.add(pointType);
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
		
		return pointTypeList;
	}

	public List<PointPinActionVO> getAvailablePinsForAction(Long boardFk,String pinType) {

		String statement = null;
		Connection conn = null;
		PreparedStatement stmt = null;
        ResultSet rs = null;

        List<PointPinActionVO> pointPinActionList = new ArrayList<PointPinActionVO>();
		try {
	    	conn = ConnectionUtility.getConnection();

	    	
	               statement = "select id , pin_number from point_pin_action where board_fk ="+boardFk+" and point_fk IS NULL and pin_type = '"+pinType+"'";
	               stmt = conn.prepareStatement(statement);
	               rs= (ResultSet) stmt.executeQuery(statement);
		  	    	 while (rs.next()) {
		  	    		PointPinActionVO pointPinAction = new PointPinActionVO();
		  	    		pointPinAction.setId(rs.getLong("id"));
		  	    		pointPinAction.setPinNumber(rs.getString("pin_number"));
		  	    		pointPinActionList.add(pointPinAction);
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

		return pointPinActionList;
	}
}


/*statement = "select dt.id ,dt.name as name, dt.digital_pins as digitalPins ,dt.analog_pins as analogPins ,dt.pwm_pins as pwmPins  from device_type as dt "
+"join board as b ON b.device_type_fk = dt.id " 
+"join point as p ON p.board_fk = b.id "
+"and p.id ="+pointId;*/
