package com.test.helloWorld.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.PointPinActionVO;
import com.test.helloWorld.service.ManagePointService;

public class GetAvailablePinsForAction extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		 List<PointPinActionVO> pointPinActionList = new ArrayList<PointPinActionVO>();
			Gson gson = new Gson();
			Long boardFk = Long.parseLong(req.getParameter("boardFk"));
			String pinType = req.getParameter("pinType");
			ManagePointService service = new ManagePointService();
			pointPinActionList = service.getAvailablePinsForAction(boardFk,pinType);
			String list = gson.toJson(pointPinActionList);
		    PrintWriter out = resp.getWriter();
		    out.write(list);
		  }
	
}
