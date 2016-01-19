package com.test.helloWorld.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.helloWorld.common.vo.BoardVO;
import com.test.helloWorld.service.ManageBoardService;

public class GetBoardsForRoom extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		 List<BoardVO> boardList = new ArrayList<>();
			Gson gson = new Gson();
			Long roomFk = Long.parseLong(req.getParameter("roomFk"));
			ManageBoardService service = new ManageBoardService();
			boardList = service.getAllBoardForRoom(roomFk);
			String list = gson.toJson(boardList);
		    PrintWriter out = resp.getWriter();
		    out.write(list);
		  }
	
}
