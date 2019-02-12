package com.qait.dao;
import java.sql.*;


public class Driver {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12278221";
	
//  Database credentials
   static final String USER = "sql12278221";
   static final String PASS = "LAYjKVAJHr";
    private Connection con;
   
   public Driver()
   {
    super();
    try {
        Class.forName(JDBC_DRIVER);
        con = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connection Established..."); 
    } catch (Exception e) {
        e.printStackTrace();
    }
	   
	  
   }
   
   public Connection getDatabaseConnection() {
	   return con;
   }
   public void close(){
       try {
           con.close();
       } catch (SQLException e) {
           e.printStackTrace();
	       
       }

   }
}
