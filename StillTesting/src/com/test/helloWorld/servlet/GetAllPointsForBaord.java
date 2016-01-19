package com.test.helloWorld.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.PointVO;
import com.test.helloWorld.service.ManagePointService;

public class GetAllPointsForBaord extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		 List<PointVO> pointList = new ArrayList<>();
			Gson gson = new Gson();
			Long boardFk = Long.parseLong(req.getParameter("boardFk"));
			ManagePointService service = new ManagePointService();
			pointList = service.getAllPointForBoard(boardFk);
			String list = gson.toJson(pointList);
		    PrintWriter out = resp.getWriter();
		    out.write(list);
		  }
	
}
