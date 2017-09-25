package com.example.wen.jdbctest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.*;
/**
 * Servlet implementation class JDBCTestServlet
 */
@WebServlet("/JDBCTestServlet")
public class JDBCTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JDBCTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();        		
		
		Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM web_student_tracker.student";
        
        try{
        	conn = dataSource.getConnection();
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(query);
        	out.println("<html><body>");
        	while(resultSet.next()){
        		out.println(resultSet.getString("email")+"<br/><br/>");
        	}
        	out.println("</body></html>");
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
