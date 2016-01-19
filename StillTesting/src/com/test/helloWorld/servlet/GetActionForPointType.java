package com.test.helloWorld.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.ActionVO;
import com.test.helloWorld.common.vo.PointVO;
import com.test.helloWorld.service.ManagePointService;

public class GetActionForPointType extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Gson gson = new Gson();
		String json = (String) req.getParameter("json");
		PointVO pointVO =  gson.fromJson(json, PointVO.class);
		ManagePointService service = new ManagePointService();
		List<ActionVO> actionList = service.getActionsForPointType(pointVO);
		String msg = gson.toJson(actionList);
	    PrintWriter out = resp.getWriter();
	    out.write(msg);
	  }
}
