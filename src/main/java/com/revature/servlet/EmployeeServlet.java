package com.revature.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.*;

import com.google.gson.Gson;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	static Logger logger = Logger.getLogger(EmployeeServlet.class);
	private static final long serialVersionUID = 1L;
    private EmployeeDao emDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("username");
		ConnectionUtil connectionUtil = new ConnectionUtil();
		emDao = new EmployeeDao(connectionUtil.getConnection());
		String method = request.getParameter("methodname");
		if(method.equals("getUserName")) {
			String displayUser = emDao.getUsername(username);
			String tryjob = "{\"displayUser\": \""+ displayUser+"\"}";
			System.out.println(tryjob);
			response.setContentType("text/html");
			response.getWriter().write(tryjob);
		}else if(method.equals("getRequestTable")) {
			List<Request> emRequest = new ArrayList<>();
			emRequest = emDao.getRequestList(username);
			Gson gson = new Gson();
			String out = gson.toJson(emRequest);
			response.setContentType("application/json");
			response.getWriter().write(out);
		}else if(method.equals("loadProfile")){
			Employee employee = emDao.getProfile(username);
			Gson gson = new Gson();
			String emString = gson.toJson(employee);
			response.setContentType("application/json");
			response.getWriter().write(emString);
		}else if(method.equals("updateProfile")) {
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			emDao.updateProfile(username,phoneNumber,email);
			logger.info("Employee "+username+" updated profile with phone number "+phoneNumber+" and email "+email);
			String out = "{\"response\": \"Updated\"}";
			response.setContentType("text/html");
			response.getWriter().write(out);
		}	
		else if(method.equals("logout")) {
			logger.info("Employee "+username+" has logged out.");
			session.invalidate();
			response.sendRedirect("Login.html");
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
