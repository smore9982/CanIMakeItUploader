package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.canimakeituploader.model.CalendarDateModel;
import com.canimakeituploader.model.RouteModel;

public class CalendarDateDao extends ParentDao {
	public void save(List<CalendarDateModel> models) throws SQLException{
		System.out.println("Saving CalendarDate");
		Connection connection =  getConnection();
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		if(connection!=null){
			try{
				String insertTableSQL = "INSERT INTO calendar_dates (calendar_date_id, service_id, date, exception) VALUES (?,?,?,?)";
				String updateTableSQL = "UPDATE calendar_dates SET service_id = ?, date = ?, exception = ? where calendar_date_id = ?";
				insertStatement = connection.prepareStatement(insertTableSQL);
				updateStatement = connection.prepareStatement(updateTableSQL);
				for(int i=0;i<models.size();i++){
					CalendarDateModel model = models.get(i);
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				    java.util.Date parsed = format.parse(model.getDate());
				    java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
				    
					insertStatement.clearParameters();
					updateStatement.clearParameters();					
					updateStatement.setString(1,model.getServiceId());			
					updateStatement.setDate(2,sqlDate );
					updateStatement.setString(3,model.getExceptionType());
					updateStatement.setString(4,model.getServiceId() + "-" + model.getDate());
				
					if(updateStatement.executeUpdate() == 0){				
						insertStatement.setString(1,model.getServiceId() + "-" + model.getDate());
						insertStatement.setString(2,model.getServiceId());			
						insertStatement.setDate(3,sqlDate );
						insertStatement.setString(4,model.getExceptionType());						
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
