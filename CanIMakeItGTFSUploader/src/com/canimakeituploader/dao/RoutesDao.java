package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.canimakeituploader.model.RouteModel;

public class RoutesDao extends ParentDao {
	public void save(List<RouteModel> models) throws SQLException{
		System.out.println("Saving routes");
		Connection connection =  getConnection();
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		if(connection!=null){
			try{
				String insertTableSQL = "INSERT INTO routes (route_id, route_short_name, route_long_name, route_type) VALUES (?,?,?,?)";
				String updateTableSQL = "UPDATE routes SET route_short_name = ?, route_long_name = ?, route_type = ? where route_id = ?";
				insertStatement = connection.prepareStatement(insertTableSQL);
				updateStatement = connection.prepareStatement(updateTableSQL);
				for(int i=0;i<models.size();i++){
					RouteModel model = models.get(i);
					insertStatement.clearParameters();
					updateStatement.clearParameters();
					updateStatement.setString(1,model.getRoute_short_name() );
					updateStatement.setString(2,model.getRoute_long_name());
					updateStatement.setString(3,model.getRoute_type() );
					updateStatement.setString(4,model.getRoute_id() );
				
					if(updateStatement.executeUpdate() == 0){				
						insertStatement.setString(1,model.getRoute_id() );
						insertStatement.setString(2,model.getRoute_short_name() );
						insertStatement.setString(3,model.getRoute_long_name() );
						insertStatement.setString(4,model.getRoute_type() );
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
