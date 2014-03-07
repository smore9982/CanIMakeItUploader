package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.canimakeituploader.model.StopModel;

public class StopsDao extends ParentDao {
	
	public void save(List<StopModel> models) throws SQLException{
		Connection connection =  getConnection();
		if(connection!=null){
			try{
				String insertTableSQL = "INSERT INTO stops (stop_id, stop_name, stop_lat, stop_lon) VALUES (?,?,?,?)";
				String updateTableSQL = "UPDATE stops SET stop_name = ?, stop_lat = ?, stop_lon = ? where stop_id = ?";
				PreparedStatement insertStatement = connection.prepareStatement(insertTableSQL);
				PreparedStatement updateStatement = connection.prepareStatement(updateTableSQL);
				for(int i=0;i<models.size();i++){
					StopModel model = models.get(i);
					insertStatement.clearParameters();
					updateStatement.clearParameters();
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
				insertStatement.close();
				updateStatement.close();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("An exception occured while trying to execure update" +e.getMessage());
			}finally{
				connection.close();
			}
		}
		else{
			return;
		}
	}
}
