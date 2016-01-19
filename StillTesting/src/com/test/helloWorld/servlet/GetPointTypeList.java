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
import com.test.helloWorld.common.vo.PointTypeVO;
import com.test.helloWorld.service.ManagePointService;

public class GetPointTypeList extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	List<PointTypeVO> pointTypeList = new ArrayList<PointTypeVO>();
	ManagePointService service = new ManagePointService();
	pointTypeList = service.getAllPointType();
	Gson gson = new Gson();
	String list = gson.toJson(pointTypeList);
    PrintWriter out = resp.getWriter();
    out.write(list);
  }

protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	
	this.doPost(req, resp);
}
}