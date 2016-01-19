package com.test.helloWorld.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.helloWorld.common.vo.UserVO;
import com.test.helloWorld.db.ConnectionUtility;

public class ManageClientService {

	public String addClient(UserVO clientVO)
	{
		return null;
	}
	
	public List<UserVO> getAllClient (){
		List<UserVO> clientList =  new ArrayList<UserVO>();
		
		Connection conn = null;
		String statement;
		Statement stmt = null;
		ResultSet rs = null;
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "SELECT id ,first_name as firstName, last_name as lastName ,company as company ,user_name as userName ,password as password ,email as email ,mobile_number as mobileNumber FROM user ";
	    	stmt = conn.createStatement();
	    	rs= (ResultSet) stmt.executeQuery(statement);
	    	 while (rs.next()) {
	                UserVO user = new UserVO();
	                user.setFirstName(rs.getString("firstName"));
	                user.setId(rs.getString("id"));
	                user.setLeaf(true);
	                clientList.add(user);
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
		
		return clientList;
	}

	
	public String authenticate(UserVO client) {
		Connection conn = null;
		String statement;
		Statement stmt = null;
		ResultSet rs = null;
		String statement1;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		String msg = "Authentication Fail";
		try {
	    	conn = ConnectionUtility.getConnection();
	    	statement = "SELECT id ,first_name as firstName, last_name as lastName FROM user where user_name = '"+client.getUserName()+"' and password = '"+client.getPassword() +"' ";
	    	stmt = conn.createStatement();
	    	rs= (ResultSet) stmt.executeQuery(statement);
	    	 while (rs.next()) {
	                UserVO user = new UserVO();
	                user.setFirstName(rs.getString("firstName"));
	                user.setId(rs.getString("id"));
	                
	                String statement2 = "UPDATE user set token_id = '"+client.getTokenId()+"' where id ="+ Long.parseLong(rs.getString("id"));
	   	    	    Statement stmt2 = conn.createStatement();
	   		        stmt2.executeUpdate(statement2);
	                
	                statement1 = "SELECT h.house_json FROM house as h where id = "+Long.parseLong(user.getId());
	    	    	stmt1 = conn.createStatement();
	    	    	rs1= (ResultSet) stmt1.executeQuery(statement1);
	    	    	while (rs1.next()) {
	    	    		 msg =  rs1.getString("house_json");
	    	    	}
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
		
		
		return msg;
	}


}
