package com.test.helloWorld.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.test.helloWorld.common.vo.HouseVO;
import com.test.helloWorld.common.vo.UserVO;
import com.test.helloWorld.service.ManageClientService;
import com.test.helloWorld.service.ManageHouseService;

public class AddHouse extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	Gson gson = new Gson();
	String json = (String) req.getParameter("json");
	HouseVO houseVO =  gson.fromJson(json, HouseVO.class);
	ManageHouseService service = new ManageHouseService();
	String msg = service.addHouse(houseVO);
	
    PrintWriter out = resp.getWriter();
    out.write(msg);
  }
}