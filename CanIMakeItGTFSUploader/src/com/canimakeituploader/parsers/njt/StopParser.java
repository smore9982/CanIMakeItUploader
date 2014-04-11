package com.canimakeituploader.parsers.njt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.dao.StopsDao;
import com.canimakeituploader.model.StopModel;

public class StopParser  implements Parser{
	List<StopModel> stops = new ArrayList<StopModel>();
	
	public void parse(File file)throws Exception{
		readStops(file);
		StopsDao dao = new StopsDao();
		dao.save(stops);		
		return;
	}
	private void readStops(File file) throws Exception {
		InputStream is = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//Skip the first line
		String line = bufferedReader.readLine();	
		line = bufferedReader.readLine();
		while(line != null && line.length() > 0){
			line = line.replace("\"", "");
			String[] tokens = line.split(",");
			if(tokens.length >=6){
				String stopId = tokens[0];
				stopId=Uploader.agencyprefix + stopId;
				
				String stopName= tokens[2];
				
				String stopLat = tokens[4];
				
				String stopLon = tokens[5];
				
				StopModel model = new StopModel(stopId,stopName,stopLat,stopLon);
				stops.add(model);
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return;
	}
}
