package com.canimakeituploader.parsers.njt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.dao.TripsDao;
import com.canimakeituploader.model.TripModel;

public class TripsParser implements Parser{
	ArrayList<TripModel> tripModels = new ArrayList<TripModel>();
	public void parse(File file) throws Exception{
		readTrips(file);
		TripsDao dao = new TripsDao();
		dao.save(tripModels);
	}
	
	private void readTrips(File file) throws Exception{
		InputStream is = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//Skip the first line
		String line = bufferedReader.readLine();	
		line = bufferedReader.readLine();
		while(line != null && line.length() > 0){
			line = line.replace("\"", "");
			String[] tokens = line.split(",");
			if(tokens.length >=5){
				String routeId = tokens[0];
				routeId=Uploader.agencyprefix + routeId;
				
				String serviceId = tokens[1];
				serviceId=Uploader.agencyprefix + serviceId;
				
				String tripId = tokens[2];
				tripId=Uploader.agencyprefix + tripId;
				
				String tripHeadSign = tokens[3];
				
				String directionId = tokens[4];
				TripModel model = new TripModel(tripId,routeId,serviceId,tripHeadSign,"", directionId);
				this.tripModels.add(model);
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return;
	}
}
