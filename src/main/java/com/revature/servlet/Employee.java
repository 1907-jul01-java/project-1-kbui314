package com.revature.servlet;

public class Employee {
	private String username;
	private String firstName;
	private String lastName;
	private String category;
	private String phoneNumber;
	private String email;
	private byte[] salt;
	private byte[] hash;
	
	public Employee(String phoneNumber,String email) {
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Employee(byte[] salt, byte[] hash, String category) {
		super();
		this.salt = salt;
		this.hash = hash;
		this.category = category;
	}

	public Employee(String username, String firstName, String lastName, String category, String phoneNumber,
			String email) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.category = category;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}
	
	
}
