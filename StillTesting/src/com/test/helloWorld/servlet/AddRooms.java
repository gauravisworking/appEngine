package com.test.helloWorld.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.RoomVO;
import com.test.helloWorld.service.ManageRoomService;

public class AddRooms extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	Gson gson = new Gson();
	String json = (String) req.getParameter("json");
	RoomVO roomVO =  gson.fromJson(json, RoomVO.class);
	ManageRoomService service = new ManageRoomService();
	String msg = service.addRoom(roomVO);
	
    PrintWriter out = resp.getWriter();
    out.write(msg);
  }
}