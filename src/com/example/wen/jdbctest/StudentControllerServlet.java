package com.example.wen.jdbctest;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web_student_tracker")
	private  DataSource dataSource;
	private StudentDBUtils studentDBUtils;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		super.init();
		try{
			studentDBUtils = new StudentDBUtils (dataSource);
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//command definition <input type="hidden" name="command" value="ADD" />
		String currentCommand = request.getParameter("command");
		if (currentCommand == null){
			currentCommand = "LIST";
		}
		
		switch (currentCommand){
		case  "LIST":
			listStudent(request,response);
			break;
		case "ADD":
			addStudent(request,response);
			break;
		default:
			listStudent(request,response);
			
		}
		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//read new student info from the jsp form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		//create new student object based the data
		Student newStudent = new Student(firstName, lastName,email);
		
		//add the new student to database
		studentDBUtils.addStudent(newStudent);
		//send back to the student info page
		listStudent(request, response);
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get data from helper class
		List<Student> students = studentDBUtils.getStudentList();
		// add data to the request
		request.setAttribute("student_info_list", students);
		//forward to jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/student-info.jsp"); 
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
