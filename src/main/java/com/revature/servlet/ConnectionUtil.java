package com.revature.servlet;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private Connection connection;
    private String url, user, password;

    /**
     * A method that connects to database by reading a file
     * with the specified url,username, and password and the
     * DriverManager gets the connection to the database
     * based on the url, user, and password
     */
    public ConnectionUtil() {
        try {
        	//System.out.println("Trying to get connection...");

			/*
			 * Properties properties = new Properties(); properties.load(new
			 * FileReader("application.properties")); this.url =
			 * properties.getProperty("url"); this.user = properties.getProperty("user");
			 * this.password = properties.getProperty("password");
			 */
        	Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://192.168.99.100:5432/reimdb","reimdb","password");
            //System.out.println("Obtained Connection...");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
		/* System.out.println("Connection is: " + connection); */
        return connection;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
        	
        }
    }
}
