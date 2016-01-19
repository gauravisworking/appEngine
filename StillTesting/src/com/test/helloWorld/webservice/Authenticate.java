package com.test.helloWorld.webservice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.UserVO;
import com.test.helloWorld.service.ManageClientService;

public class Authenticate extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Gson gson = new Gson();
		String json = (String) req.getParameter("json");
		
		UserVO client = gson.fromJson(json, UserVO.class);
		
		ManageClientService service = new ManageClientService();
		String msg = service.authenticate(client);
		
	    PrintWriter out = resp.getWriter();
	    out.write(msg);
	  }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doPost(req, resp);
	}
}
