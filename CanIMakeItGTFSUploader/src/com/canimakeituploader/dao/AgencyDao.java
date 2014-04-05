package com.canimakeituploader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.canimakeituploader.model.AgencyModel;

public class AgencyDao extends ParentDao {
	public void save(AgencyModel model) throws SQLException{
		System.out.println("Saving agency");
		Connection connection =  getConnection();
		PreparedStatement insertStatement = null;
		PreparedStatement updateStatement = null;
		if(connection!=null){
			try{
				String insertTableSQL = "INSERT INTO agency (agency_id,agency_name,agency_url,agency_timezone,agency_phone) VALUES (?,?,?,?,?)";
				String updateTableSQL = "UPDATE agency SET agency_name = ?, agency_url = ?, agency_timezone = ?, agency_phone = ? where agency_id = ?";
				insertStatement = connection.prepareStatement(insertTableSQL);
				updateStatement = connection.prepareStatement(updateTableSQL);
					updateStatement.setString(1,model.getAgency_id());
					updateStatement.setString(2,model.getAgency_name());
					updateStatement.setString(3,model.getAgency_url());
					updateStatement.setString(4,model.getAgency_timezone());
					updateStatement.setString(5,model.getAgency_phone());
				
					if(updateStatement.executeUpdate() == 0){										
						insertStatement.setString(1,model.getAgency_id());
						insertStatement.setString(2,model.getAgency_name());
						insertStatement.setString(3,model.getAgency_url());
						insertStatement.setString(4,model.getAgency_timezone());
						insertStatement.setString(5,model.getAgency_phone());
						insertStatement.executeUpdate();
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
