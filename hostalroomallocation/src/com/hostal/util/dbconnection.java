	package com.hostal.util;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class dbconnection {

	    private static final String URL =
	            "jdbc:mysql://localhost:3306/hostalroomallocation";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Gopi@2006";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}

