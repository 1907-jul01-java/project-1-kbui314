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
 * Servlet implementation class ManagerServlet
 */
public class ManagerServlet extends HttpServlet {
	static Logger logger = Logger.getLogger(ManagerServlet.class);
	private static final long serialVersionUID = 1L;
    private EmployeeDao emDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
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
			response.setContentType("text/html");
			response.getWriter().write(tryjob);
		}else if(method.equals("getRequestTable")) {
			List<Request> emRequest = new ArrayList<>();
			emRequest = emDao.getRequestList(username);
			Gson gson = new Gson();
			String out = gson.toJson(emRequest);
			response.setContentType("application/json");
			response.getWriter().write(out);
		}else if(method.equals("Accepted") || method.equals("Denied")){
			String id = request.getParameter("requestId");
			int request_id = Integer.parseInt(id);
			emDao.processRequest(request_id, method,username);
			logger.info("Manager "+username+" "+method+" with request ID of "+request_id);
			String process = "{\"process\": \""+ method+"\"}";
			response.setContentType("text/html");
			response.getWriter().write(process);
		}else if(method.equals("getEmployees")) {
			List<Employee> employeeList = new ArrayList<>();
			employeeList = emDao.getAllEmployees();
			Gson gson = new Gson();
			String out = gson.toJson(employeeList);
			response.setContentType("application/json");
			response.getWriter().write(out);
		}else if(method.equals("getImage")){
			String requestId = (String)request.getParameter("requestId");
			int id = Integer.parseInt(requestId);
			String imageBase64 = emDao.getImage(id);
			String out = "{\"image\": \""+ imageBase64+"\"}";
			response.setContentType("text/html");
			response.getWriter().write(out);
		}else if(method.equals("logout")) {
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
