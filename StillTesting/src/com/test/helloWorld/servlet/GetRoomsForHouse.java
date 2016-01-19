package com.test.helloWorld.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.RoomVO;
import com.test.helloWorld.service.ManageRoomService;

public class GetRoomsForHouse extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		 List<RoomVO> roomList = new ArrayList<>();
			Gson gson = new Gson();
			Long houseFk = Long.parseLong(req.getParameter("houseFk"));
			ManageRoomService service = new ManageRoomService();
			roomList = service.getAllRoomForHouse(houseFk);
			String list = gson.toJson(roomList);
		    PrintWriter out = resp.getWriter();
		    out.write(list);
		  }
	
}
