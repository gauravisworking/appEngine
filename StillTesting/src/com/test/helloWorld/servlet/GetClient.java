package com.test.helloWorld.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.test.helloWorld.common.vo.UserVO;
import com.test.helloWorld.service.ManageClientService;

public class GetClient extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	List<UserVO> clientList = new ArrayList<>();
	ManageClientService service = new ManageClientService();
	clientList = service.getAllClient();
	Gson gson = new Gson();
	String list = gson.toJson(clientList);
    PrintWriter out = resp.getWriter();
    out.write(list);
  }
}