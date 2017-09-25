package com.example.wen.jdbctest;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDBUtils {
	@Resource(name="jdbc/web_student_tracker")
	private  DataSource dataSource;
	public StudentDBUtils(DataSource dataSource){
		this.dataSource = dataSource;
	}
	public  ArrayList<Student> getStudentList(){
		ArrayList<Student> students = new ArrayList<Student> ();
		
		Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM web_student_tracker.student ORDER BY last_name";
        
        try{
        	conn = dataSource.getConnection();
        	statement = conn.createStatement();
        	resultSet = statement.executeQuery(query);
        
        	while(resultSet.next()){
        		students.add(new Student(resultSet.getInt("id"),resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getString("email")));
        	}
        	conn.close();
        	
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	close(conn,statement,resultSet);
        }
        
		return students;
	}
	private void close(Connection conn, Statement statement, ResultSet resultSet) {
		// TODO Auto-generated method stub
		try{
			if (conn != null){
				conn.close();
			}
			if (statement != null){
				statement.close();
			}
			if (resultSet != null){
				resultSet.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	public void addStudent(Student newStudent) {
		// TODO Auto-generated method stub
		//insert new student to db
	}


}
