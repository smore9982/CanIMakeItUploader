package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.canimakeituploader.model.TripModel;

public class TripsDao extends ParentDao{
	public void save(List<TripModel> models) throws SQLException{
		System.out.println("Saving trips");
		Connection connection =  getConnection();
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		if(connection!=null){
			try{
				String insertTableSQL = "INSERT INTO trips (trip_id,route_id,service_id,trip_headsign,trip_direction) VALUES (?,?,?,?,?)";
				String updateTableSQL = "UPDATE trips SET route_id = ?, service_id = ?, trip_headsign = ?, trip_direction = ? where trip_id = ?";
				insertStatement = connection.prepareStatement(insertTableSQL);
				updateStatement = connection.prepareStatement(updateTableSQL);
				for(int i=0;i<models.size();i++){
					TripModel model = models.get(i);
					insertStatement.clearParameters();
					updateStatement.clearParameters();
					updateStatement.setString(1,model.getRoute_id() );
					updateStatement.setString(2,model.getService_id() );
					updateStatement.setString(3,model.getTrip_headsign());
					updateStatement.setString(4, model.getDirection_id());
					updateStatement.setString(5,model.getTrip_id());					
				
					if(updateStatement.executeUpdate() == 0){										
						insertStatement.setString(1,model.getTrip_id());
						insertStatement.setString(2,model.getRoute_id() );
						insertStatement.setString(3,model.getService_id() );
						insertStatement.setString(4,model.getTrip_headsign());
						updateStatement.setString(5, model.getDirection_id());
						insertStatement.executeUpdate();
					}				
				}
				insertStatement.close();
				updateStatement.close();
			}catch(Exception e){
				try{
					if(insertStatement !=null){
						insertStatement.close();
					}
				}catch(Exception e1){};
				
				try{
					if (updateStatement !=null ){
						updateStatement.close();
					}
				}catch(Exception e2){};
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
