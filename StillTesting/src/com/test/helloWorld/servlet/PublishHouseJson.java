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
import com.test.helloWorld.service.ManageClientService;
import com.test.helloWorld.service.ManageHouseService;

public class PublishHouseJson extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		 String msg = null;
			Long houseFk = Long.parseLong(req.getParameter("houseFk"));
			ManageHouseService service = new ManageHouseService();
			msg = service.publishHouseJson(houseFk);
		    PrintWriter out = resp.getWriter();
		    out.write(msg);
		  }
	
}
