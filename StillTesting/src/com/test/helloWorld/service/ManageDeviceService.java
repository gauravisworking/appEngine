package com.test.helloWorld.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.helloWorld.common.vo.DeviceTypeVO;
import com.test.helloWorld.db.ConnectionUtility;

public class ManageDeviceService {

	public List<DeviceTypeVO> getAllDeviceType() {

		Connection conn = null;
		String statement;
		Statement stmt = null;
		ResultSet rs = null;
		List<DeviceTypeVO> deviceTypeList = new ArrayList<DeviceTypeVO>();
		try {
	    	conn = ConnectionUtility.getConnection();

	    	statement = "SELECT id ,name as name, digital_pins as digitalPins ,analog_pins as analogPins ,pwm_pins as pwmPins FROM device_type ";
	    	stmt = conn.createStatement();
	    	rs= (ResultSet) stmt.executeQuery(statement);
	    	 while (rs.next()) {
	                DeviceTypeVO deviceType = new DeviceTypeVO();
	                deviceType.setId(rs.getLong("id"));
	                deviceType.setName(rs.getString("name"));
	                deviceType.setDigitalPins(rs.getString("digitalPins"));
	                deviceType.setAnalogPins(rs.getString("analogPins"));
	                deviceType.setPwmPins(rs.getString("pwmPins"));
	                deviceTypeList.add(deviceType);
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
		
		return deviceTypeList;
	}

}
