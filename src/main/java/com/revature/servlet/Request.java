package com.revature.servlet;

public class Request {
	private int request_id;
	private String username;
	private String description;
	private double amount;
	private String status;
	private String date;
	private String manager;
	public Request(int request_id, String username, String description, double amount, String status, String date,String manager) {
		super();
		this.request_id = request_id;
		this.username = username;
		this.description = description;
		this.amount = amount;
		this.status = status;
		this.date = date;
		this.manager = manager;
	}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
}
