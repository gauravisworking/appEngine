package com.test.helloWorld.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.HouseVO;
import com.test.helloWorld.service.ManageHouseService;

public class GetAllHouses extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	List<HouseVO> houseList = new ArrayList<>();
	ManageHouseService service = new ManageHouseService();
	houseList = service.getAllHouses();
	Gson gson = new Gson();
	String list = gson.toJson(houseList);
    PrintWriter out = resp.getWriter();
    out.write(list);
  }
}