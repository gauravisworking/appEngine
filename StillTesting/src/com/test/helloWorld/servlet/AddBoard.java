package com.test.helloWorld.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.BoardVO;
import com.test.helloWorld.service.ManageBoardService;

public class AddBoard extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Gson gson = new Gson();
		String json = (String) req.getParameter("json");
		BoardVO boardVO =  gson.fromJson(json, BoardVO.class);
		ManageBoardService service = new ManageBoardService();
		String msg = service.addBoard(boardVO);
		
	    PrintWriter out = resp.getWriter();
	    out.write(msg);
	  }
}
