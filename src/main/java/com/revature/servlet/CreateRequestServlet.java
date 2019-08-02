package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.*;

/**
 * Servlet implementation class CreateRequestServlet
 */
public class CreateRequestServlet extends HttpServlet {
	static Logger logger = Logger.getLogger(CreateRequestServlet.class);
	private static final long serialVersionUID = 1L;
    private EmployeeDao emDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ConnectionUtil connectionUtil = new ConnectionUtil();
			HttpSession session = request.getSession(false);
			emDao = new EmployeeDao(connectionUtil.getConnection());
			String username = (String)session.getAttribute("username");
			String description = request.getParameter("description");
			String amount = request.getParameter("amount");
			String date = request.getParameter("date");
			double currency = Double.parseDouble(amount);
			String manager = "N/A";
			if(request.getParameter("methodname").equals("request")) {
				String image = "No images were uploaded";
				emDao.insertRequest(username,description,currency,date,manager,image);
				logger.info("Employee "+username+" submitted a request without an image");
			}else {
				String image = request.getParameter("img");
				emDao.insertRequest(username, description, currency, date, manager, image);
				logger.info("Employee "+username+" submitted a request with an image");
			}
			String send = "Request has been sent";
			String message = "{\"displayMessage\": \""+send+"\"}";
			response.setContentType("text/html");
			response.getWriter().write(message);
		} catch(Exception e){
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
