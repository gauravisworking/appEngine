package com.test.helloWorld.db;
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;

public class GuestbookServlet extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    PrintWriter out = resp.getWriter();
    try {
      Connection conn = ConnectionUtility.getConnection();
      try {
        String fname = req.getParameter("fname");
        String content = req.getParameter("content");
        if (fname == "" || content == "") {
          out.println(
              "<html><head></head><body>You are missing either a message or a name! Try again! " +
              "Redirecting in 3 seconds...</body></html>");
        } else {
          String statement = "INSERT INTO entries (guestName, content) VALUES( ? , ? )";
          PreparedStatement stmt = conn.prepareStatement(statement);
          stmt.setString(1, fname);
          stmt.setString(2, content);
          int success = 2;
          success = stmt.executeUpdate();
          if (success == 1) {
            out.println(
                "<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
          } else if (success == 0) {
            out.println(
                "<html><head></head><body>Failure! Please try again! " +
                "Redirecting in 3 seconds...</body></html>");
          }
        }
      } finally {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    resp.setHeader("Refresh", "3; url=/guestbook.jsp");
  }
}