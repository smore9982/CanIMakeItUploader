package com.canimakeituploader.parsers;

import java.util.ArrayList;
import java.util.List;

import com.canimakeituploader.model.StopModel;
import com.google.gson.stream.JsonReader;

public class StopParser  implements Parser{
	List<StopModel> stops = new ArrayList<StopModel>();
	
	public void parse(JsonReader jsonReader)throws Exception{
		readStops(jsonReader);				
		for(int i=0;i<stops.size();i++){
			System.out.println(stops.get(i));
		}
		
		return;
	}
	private void readStops(JsonReader jsonReader) throws Exception {
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readStop(jsonReader);
		}
		jsonReader.endArray();
	}

	private void readStop(JsonReader jsonReader) throws Exception {
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();
			if(name.equals("stop")){
				readCurrentStop(jsonReader);
			}
		}
		jsonReader.endObject();
	}

	private void readCurrentStop(JsonReader jsonReader) throws Exception {
		jsonReader.beginObject();
		String stopId = "";
		String stopName = "";
		String stopLat = "";
		String stopLon = "";
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();			
			if(name.equals("stop_id")){
				stopId=jsonReader.nextString();
			}else if(name.equals("stop_name")){
				stopName = jsonReader.nextString();
			}else if(name.equals("stop_lat")){
				stopLat = jsonReader.nextString();
			}else if(name.equals("stop_lon")){
				stopLon = jsonReader.nextString();
			}
		}
		jsonReader.endObject();
		if(stopId!=null && stopId.length() >0){
			StopModel model = new StopModel(stopId,stopName,stopLat,stopLon);
			stops.add(model);
		}
	}
}
