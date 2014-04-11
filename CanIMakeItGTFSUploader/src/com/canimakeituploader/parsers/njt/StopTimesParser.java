package com.canimakeituploader.parsers.njt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.dao.StopTimeDao;
import com.canimakeituploader.model.StopTimeModel;

public class StopTimesParser  implements Parser {
	private ArrayList<StopTimeModel> stopTimeModelList = new ArrayList<StopTimeModel>();
	
	public void parse(File file) throws Exception{
		readStopTimes(file);
		StopTimeDao dao = new StopTimeDao();
		dao.save(stopTimeModelList);
		return;
	}
	
	private void readStopTimes(File file) throws Exception{
		InputStream is = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//Skip the first line
		String line = bufferedReader.readLine();	
		line = bufferedReader.readLine();
		while(line != null && line.length() > 0){
			line = line.replace("\"", "");
			String[] tokens = line.split(",");
			if(tokens.length >=5){
				String tripId = tokens[0];
				tripId = Uploader.agencyprefix + tripId;
				
				String arrivalTime = tokens[1];
				
				String departureTime = tokens[2];
				
				String stopId = tokens[3];
				stopId = Uploader.agencyprefix + stopId;
				
				String stopSeq = tokens[4];
				
				StopTimeModel model = new StopTimeModel(tripId,stopId,arrivalTime,departureTime,stopSeq);
				stopTimeModelList.add(model);
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return;
	}
}
