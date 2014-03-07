package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.canimakeituploader.model.StopModel;

public class StopsDao {
	
	public void save(List<StopModel> models) throws SQLException{
		Connection connection =  getConnection();
		if(connection!=null){
			System.out.println("GOT A CONNECTION");
			String insertTableSQL = "INSERT INTO stops (stop_id, stop_name, stop_lat, stop_lon) VALUES (?,?,?,?)";
			String updateTableSQL = "UPDATE stops SET stop_name = ?, stop_lat = ?, stop_lon = ? where stop_id = ?";
			
			for(int i=0;i<models.size();i++){
				StopModel model = models.get(i);
				PreparedStatement insertStatement = connection.prepareStatement(insertTableSQL);
				PreparedStatement updateStatement = connection.prepareStatement(updateTableSQL);
				
				updateStatement.setString(1,model.getStop_name() );
				updateStatement.setString(2,model.getStop_lat() );
				updateStatement.setString(3,model.getStop_lon() );
				updateStatement.setString(4,model.getStop_id() );
				
				if(updateStatement.executeUpdate() == 0){				
					insertStatement.setString(1,model.getStop_id() );
					insertStatement.setString(2,model.getStop_name() );
					insertStatement.setString(3,model.getStop_lat() );
					insertStatement.setString(4,model.getStop_lon() );					
					insertStatement.executeUpdate();
				}				
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection(){
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
