package com.liquid.atmo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker1?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "hb_student";
		String pass = "hb_student";
		
		try {
		    System.out.println("Connecting to database: " + jdbcUrl);
		    
		    Connection myConn = 
		    		DriverManager.getConnection(jdbcUrl, user, pass);
		    
		    System.out.println("Connection successful!!!");
		    		
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
