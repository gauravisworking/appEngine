package com.test.helloWorld.db;


import java.sql.*;
import com.google.appengine.api.utils.SystemProperty;

public class ConnectionUtility {

	public static Connection getConnection(){
		Connection conn = null;
		String url=null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) 
			{
		        Class.forName("com.mysql.jdbc.GoogleDriver");
		        url = "jdbc:google:mysql://ace-metric-108720:testhelloworld/guestbook?user=root&password=root";
			} 
			else 
			{
		        Class.forName("com.mysql.jdbc.Driver");
		        //url = "jdbc:mysql://173.194.86.110:3306/guestbook?user=root&password=root";
		        url = "jdbc:mysql://localhost:3306/guestbook?user=root&password=root";
			}
			conn = (Connection) DriverManager.getConnection(url);
			conn.setAutoCommit(false);
	    } 
		catch (Exception e) 
		{
	      e.printStackTrace();
	      return null;
	    }
	    return conn;
	}
	
	public static void closeConnection(Connection conn){
		try{
			conn.commit();
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
