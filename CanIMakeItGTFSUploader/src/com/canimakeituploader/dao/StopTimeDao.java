package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.canimakeituploader.model.StopTimeModel;
import java.sql.Time;

public class StopTimeDao extends ParentDao {
	public void save(List<StopTimeModel> models) throws SQLException{
		System.out.println("Saving stop times");
		Connection connection =  getConnection();
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		if(connection!=null){
			try{
				String insertTableSQL = "INSERT INTO stop_times (stop_time_id,trip_id, arrival_time,departure_time,stop_id,stop_sequence) VALUES (?,?,?,?,?,?)";
				String updateTableSQL = "UPDATE stop_times SET trip_id = ?, arrival_time = ?, departure_time = ? , stop_id = ? , stop_sequence = ? where stop_time_id = ?";
				
				insertStatement = connection.prepareStatement(insertTableSQL);
				updateStatement = connection.prepareStatement(updateTableSQL);
				for(int i=0;i<models.size();i++){
					StopTimeModel model = models.get(i);
					insertStatement.clearParameters();
					updateStatement.clearParameters();
					
					Time arrivalTime = Time.valueOf(model.getArrival_time());
					Time departureTime = Time.valueOf(model.getDeparture_time());
					
					updateStatement.setString(1,model.getTrip_id() );
					updateStatement.setTime(2,arrivalTime );
					updateStatement.setTime(3,departureTime );
					updateStatement.setString(4,model.getStop_id());
					updateStatement.setString(5,model.getStop_sequence() );
					updateStatement.setString(6,model.getTrip_id() + "-"+model.getStop_id() );
				
					if(updateStatement.executeUpdate() == 0){	
						insertStatement.setString(1,model.getTrip_id() + "-"+model.getStop_id() );
						insertStatement.setString(2,model.getTrip_id() );
						updateStatement.setTime(3,arrivalTime );
						updateStatement.setTime(4,departureTime );
						insertStatement.setString(5,model.getStop_id());
						insertStatement.setString(6,model.getStop_sequence() );
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
