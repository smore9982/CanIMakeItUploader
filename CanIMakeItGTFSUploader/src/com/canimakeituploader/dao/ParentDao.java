package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ParentDao {
	protected Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CanIMakeItDB","root", "");
			return connection;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
