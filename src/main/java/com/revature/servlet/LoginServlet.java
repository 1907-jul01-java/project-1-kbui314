package com.revature.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
/*import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;*/
import java.util.Arrays;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	static Logger logger = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	private EmployeeDao emDao;

	public LoginServlet() {
		super();
	}
	
	public void init() throws ServletException{
		

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ConnectionUtil connectionUtil = new ConnectionUtil();
			HttpSession session = req.getSession(false);
			emDao = new EmployeeDao(connectionUtil.getConnection());
			String userName = req.getParameter("username");
			String userPass = req.getParameter("passwordken");
			Employee employee = emDao.getPassword(userName);
			if(employee.equals(null)) {
				logger.info("Login Unsuccessful");
				resp.sendRedirect("Login.html");
			}else {
				byte[] salt = employee.getSalt();
				byte[] hash = employee.getHash();
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				md.update(salt);
				byte[] hashedUserPass = md.digest(userPass.getBytes(StandardCharsets.UTF_8));
				if(Arrays.equals(hash, hashedUserPass)) {
					session = req.getSession();
					session.setAttribute("username", userName);
					if(employee.getCategory().equals("manager")) {
						logger.info(userName+" logined as Manager");
						resp.sendRedirect("Manager.html");
					}else {
						logger.info(userName+" logined as Employee");
						resp.sendRedirect("Employee.html");
					}
				}else {
					logger.info("Login Unsuccessful");
					resp.sendRedirect("Login.html");
				}
			}
			
//			if(emDao.validate(userName, userPass)) {
//				session = req.getSession();
//				session.setAttribute("username", userName);
//				if(emDao.getCategory(userName).equals("manager")) {
//					resp.sendRedirect("Manager.html");
//				}else {
//					resp.sendRedirect("Employee.html");
//				}
//			}else {
//		        resp.sendRedirect("Login.html");
//			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req,resp);
	}
	
}
