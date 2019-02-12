package com.qait.dao;

import java.sql.*;

public class Main {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		 Driver driver = new Driver();
		 Connection con = driver.getDatabaseConnection();
		 
		 Operation operate = new Operation(con);
		System.out.println(operate.readCommentByUser("dharmendragupta@qainfotech.com"));
		
		 
	}
}
