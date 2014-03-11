package com.canimakeituploader.parsers;

import java.util.ArrayList;

import com.canimakeituploader.Util;
import com.canimakeituploader.dao.StopTimeDao;
import com.canimakeituploader.dao.StopsDao;
import com.canimakeituploader.model.StopModel;
import com.canimakeituploader.model.StopTimeModel;
import com.google.gson.stream.JsonReader;

public class StopTimesParser  implements Parser {
	private ArrayList<StopTimeModel> stopTimeModelList = new ArrayList<StopTimeModel>();
	
	public void parse(JsonReader jsonReader) throws Exception{
		readStopTimes(jsonReader);
		StopTimeDao dao = new StopTimeDao();
		dao.save(stopTimeModelList);
		return;
	}
	
	private void readStopTimes(JsonReader jsonReader) throws Exception{
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readStopTime(jsonReader);
		}
		jsonReader.endArray();
	}
	
	private void readStopTime(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();	
			if(name.equals("stop_time")){
				readCurrentStopTime(jsonReader);
			}
		}
		jsonReader.endObject();
	}
	private void readCurrentStopTime(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		String stopId = "";
		String tripId = "";
		String departureTime = "";
		String arrivalTime = "";
		String sequence = "";
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();			
			if(name.equals("stop_id")){
				stopId=jsonReader.nextString();
			}else if(name.equals("trip_id")){
				tripId = jsonReader.nextString();
			}else if(name.equals("departure_time")){
				departureTime = jsonReader.nextString();
			}else if(name.equals("arrival_time")){
				arrivalTime = jsonReader.nextString();
			}else if(name.equals("stop_sequence")){
				sequence = jsonReader.nextString();
			}
		}
		jsonReader.endObject();
		if(stopId!=null && stopId.length() >0 && tripId!=null && tripId.length() !=0){
			StopTimeModel model = new StopTimeModel(tripId,stopId,arrivalTime,departureTime,sequence);
			stopTimeModelList.add(model);
		}
	}
}
