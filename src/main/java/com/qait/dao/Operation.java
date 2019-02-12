package com.qait.dao;

import java.sql.*;
import org.json.*;


public class Operation {
	private Connection con;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String query = "";

	public Operation(Connection con) {
		this.con = con;
	}

	public int signup(String UName, String UEmail, String UPassword) {
		try {
			query = "insert into user (UName, UEmail, UPassword) values ( ?, ?, ?)";
			PreparedStatement insertStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			insertStatement.setString(1, UName);
			insertStatement.setString(2, UEmail);
			insertStatement.setString(3, UPassword);

			insertStatement.execute();
			resultSet = insertStatement.getGeneratedKeys();

			if (resultSet != null && resultSet.next()) {

				return resultSet.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int addComment(String UEmail, String UComment) {
		try {
			query = "insert into comment (UEmail, UComment) values (?, ?)";
			PreparedStatement insertStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			insertStatement.setString(1, UEmail);
			insertStatement.setString(2, UComment);

			insertStatement.executeUpdate();
			resultSet = insertStatement.getGeneratedKeys();

			if (resultSet != null && resultSet.next()) {
				return resultSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("inserted");
		return 0;
	}

	public String readCommentTable() {
		JSONArray jsonArray = new JSONArray();
		try {
			
			statement = con.createStatement();
			resultSet = statement.executeQuery("select * from comment");
			while (resultSet.next()) {
				int CId = resultSet.getInt(1);
				String UEmail = resultSet.getString(2);
				String UComment = resultSet.getString(3);
				JSONObject obj = new JSONObject();
				obj.put("cid",CId);
				obj.put("email",UEmail);
				obj.put("comment",UComment);
				jsonArray.put(obj);
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject mainObj = new JSONObject();
		mainObj.put("result", jsonArray);
		if(!mainObj.getJSONArray("result").isEmpty()){
			return mainObj.toString();
		}
		return "";
	}
	
	public String getPassword(String UEmail) {
		String pass = "";
		try {
			statement = con.createStatement();
			query = "select UPassword from user where UEmail= \"" + UEmail+"\";";
			resultSet = statement.executeQuery(query);
			resultSet.next();
			pass = resultSet.getString("UPassword");
			return pass;
		} catch (SQLException e) {
			if(e.toString().equals("java.sql.SQLException: Illegal operation on empty result set."))
			{
				return null;
			}
		}
		return pass;
	}

	public String readCommentByUser(String Email) {
		JSONArray jsonArray = new JSONArray();
		try {
			
			statement = con.createStatement();
			query = "select * from comment where UEmail= \"" + Email+"\";";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int CId = resultSet.getInt(1);
				String UEmail = resultSet.getString(2);
				String UComment = resultSet.getString(3);
				JSONObject obj = new JSONObject();
				obj.put("cid",CId);
				obj.put("email",UEmail);
				obj.put("comment",UComment);
				jsonArray.put(obj);
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject mainObj = new JSONObject();
		mainObj.put("result", jsonArray);
		if(!mainObj.getJSONArray("result").isEmpty()){
			return mainObj.toString();
		}
		return null;
	}
}

	

	
	
	
	
	
		
	
	
