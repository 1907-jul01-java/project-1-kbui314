package com.revature.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	Connection connection;
	public EmployeeDao() {
		super();
	};
	
	public EmployeeDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public boolean validate(String username, String password) {
		boolean status = false;
		try{
			PreparedStatement pStatement = connection.prepareStatement("Select password from employee where username=?");
			pStatement.setString(1, username);
			ResultSet resultSet = pStatement.executeQuery();
			
			while(resultSet.next()) {
				if(password.equals(resultSet.getString("password"))) {
					status = true;
					break;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
	public Employee getPassword(String username) {
		Employee employee = null;
		try {
			PreparedStatement pStatement = connection.prepareStatement("Select salt,hash,category from employee where username=?");
			pStatement.setString(1, username);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				byte[] salt = resultSet.getBytes("salt");
				byte[] hash = resultSet.getBytes("hash");
				String category = resultSet.getString("category");
				employee = new Employee(salt,hash,category);
				return employee;
			}
		}catch(Exception e){
			
		}
		return employee;
	}
	
	public String getUsername(String username) {
		String userName = "";
		try {
			PreparedStatement pStatement = connection.prepareStatement("Select firstname,lastname from employee where username=?");
			pStatement.setString(1, username);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				userName = resultSet.getString("firstname")+" "+resultSet.getString("lastname");
				return userName;
			}
		}catch(Exception e) {
			
		}
		return userName;
		
	}
	
	public void insertRequest(String username,String description,double amount,String date,String manager,String image) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("Insert into request(username,description,amount,date,status,manager,image) values(?,?,?,?,?,?,?)");
			pStatement.setString(1, username);
			pStatement.setString(2, description);
			pStatement.setDouble(3, amount);
			pStatement.setString(4, date);
			pStatement.setString(5, "Pending");
			pStatement.setString(6, manager);
			pStatement.setString(7, image);
			pStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Request> getRequestList(String username){
		List<Request> emRequest = new ArrayList<>();
		try {
			PreparedStatement pStatement;
			if(getCategory(username).equals("employee")) {
				pStatement = connection.prepareStatement("Select request_id,username,description,amount,status,date,manager from request where username=?");
				pStatement.setString(1, username);
			}else {
				pStatement = connection.prepareStatement("Select * from request");
			}
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("request_id");
				String userName = resultSet.getString("username");
				String description = resultSet.getString("description");
				double amount = resultSet.getDouble("amount");
				String status = resultSet.getString("status");
				String date = resultSet.getString("date");
				String manager = resultSet.getString("manager");
				Request req = new Request(id,userName,description,amount,status,date,manager);
				emRequest.add(req);
			}
			return emRequest;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return emRequest;
	}
	
	public String getCategory(String username) {
		String category="";
		try {
			PreparedStatement pStatement = connection.prepareStatement("Select category from employee where username=?");
			pStatement.setString(1, username);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				category = resultSet.getString("category");
				return category;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	//-----------------------------------------------
	public void processRequest(int id,String action,String manager) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("Update request set status=?,manager=? where request_id=?");
			pStatement.setString(1, action);
			pStatement.setString(2, manager);
			pStatement.setInt(3, id);
			pStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		try {
			PreparedStatement pStatement = connection.prepareStatement("Select * from employee");
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String username = resultSet.getString("username");
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String category = resultSet.getString("category");
				String phoneNumber = resultSet.getString("phone_number");
				String email = resultSet.getString("email");
				employee = new Employee(username,firstName,lastName,category,phoneNumber,email);
				employeeList.add(employee);
			}
			return employeeList;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}
	
	public Employee getProfile(String username){
		Employee employee = null;
		try {
			PreparedStatement pStatement = connection.prepareStatement("Select phone_number,email from employee where username=?");
			pStatement.setString(1, username);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				String phoneNumber = resultSet.getString("phone_number");
				String email = resultSet.getString("email");
				employee = new Employee(phoneNumber,email);
				break;
			}
			return employee;
		}catch(Exception e){
			e.printStackTrace();
		}
		return employee;
	}
	
	public void updateProfile(String username,String phoneNumber,String email) {
		try {
			PreparedStatement pStatement = connection.prepareStatement("Update employee set phone_number=?,email=? where username=?");
			pStatement.setString(1, phoneNumber);
			pStatement.setString(2, email);
			pStatement.setString(3, username);
			pStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getImage(int requestId) {
		String image = "";
		try {
			PreparedStatement pStatement = connection.prepareStatement("Select image from request where request_id=?");
			pStatement.setInt(1,requestId);
			ResultSet resultSet = pStatement.executeQuery();
			while(resultSet.next()) {
				image = resultSet.getString("image");
				return image;
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		return image;
	}
}
