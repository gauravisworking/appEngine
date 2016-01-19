package com.test.helloWorld.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.DeviceTypeVO;
import com.test.helloWorld.service.ManageDeviceService;

public class GetDeviceTypeList extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	List<DeviceTypeVO> deviceTypeList = new ArrayList<DeviceTypeVO>();
	ManageDeviceService service = new ManageDeviceService();
	deviceTypeList = service.getAllDeviceType();
	Gson gson = new Gson();
	String list = gson.toJson(deviceTypeList);
    PrintWriter out = resp.getWriter();
    out.write(list);
  }

protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	
	this.doPost(req, resp);
}
}