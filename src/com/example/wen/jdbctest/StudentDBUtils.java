package com.example.wen.jdbctest;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		//get connection and statement
		Connection conn = null;
		PreparedStatement preparedstat = null;
		//get table column information based  on the student object
		String firstName = newStudent.getFirstName();
		String lastName = newStudent.getLastName();
		String email = newStudent.getEmail();
		//create sql
		//INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)  
		//VALUES (value1, value2, value3,...valueN);
		String query = "INSERT INTO web_student_tracker.student (first_name,last_name,email) VALUES(?,?,?)";
		
		try{
			conn = dataSource.getConnection();
			preparedstat = conn.prepareStatement(query);			
			
			preparedstat.setString(1,firstName);
			preparedstat.setString(2, lastName);
			preparedstat.setString(3, email);			
			//execute sql
			preparedstat.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//clean up jdbc object
			close(conn,preparedstat, null);
		}
		
	}
	public Student getStudent(String studentId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet result = null;
		Student currentStudent = null;
		String query = "SELECT * FROM web_student_tracker.student WHERE id = ?";
		
		try{
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(query);
			stat.setInt(1,Integer.parseInt(studentId));
			result = stat.executeQuery();
			
			if(result.next()){
				currentStudent = new Student(Integer.parseInt(studentId),result.getString("first_name"),result.getString("last_name"),result.getString("email"));
			}else{
				System.out.println("could not find this student id = "+studentId);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(conn, stat, result);
		}
		return currentStudent;
	}
	public void updateStudent(Student studentToUpdate) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stat = null;
		String query = "UPDATE web_student_tracker.student SET first_name = ?, last_name = ?, email = ? WHERE id = ?";	    
		try{
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(query);
			stat.setString(1, studentToUpdate.getFirstName());
			stat.setString(2, studentToUpdate.getLastName());
			stat.setString(3, studentToUpdate.getEmail());
			stat.setInt(4, studentToUpdate.getId());	
			
			stat.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(conn, stat, null);
		}
		
	}
	public void deleteStudentById(int id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stat = null;
		String query = "DELETE FROM web_student_tracker.student WHERE id = ?";
		
		try{
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(query);
			stat.setInt(1, id);
			
			stat.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(conn,stat,null);
		}
		
	}


}
