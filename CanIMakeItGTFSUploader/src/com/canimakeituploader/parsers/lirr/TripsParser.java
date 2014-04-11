package com.canimakeituploader.parsers.lirr;

import java.util.ArrayList;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.Util;
import com.canimakeituploader.dao.TripsDao;
import com.canimakeituploader.model.TripModel;
import com.google.gson.stream.JsonReader;

public class TripsParser{
	ArrayList<TripModel> tripModels = new ArrayList<TripModel>();
	public void parse(JsonReader jsonReader) throws Exception{
		readTrips(jsonReader);
		TripsDao dao = new TripsDao();
		dao.save(tripModels);
	}
	
	private void readTrips(JsonReader jsonReader) throws Exception{
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readTrip(jsonReader);			
		}
		jsonReader.endArray();
		return;
	}
	
	private void readTrip(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();	
			if(name.equals("trip")){
				readCurrentTrip(jsonReader);
			}
		}
		jsonReader.endObject();
	}
	private void readCurrentTrip(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		String routeId = "";
		String tripId = "";
		String serviceId = "";
		String headsign = "";
		String directionId = "";
		String shortName = "";
		while(jsonReader.hasNext()){			
			String name = jsonReader.nextName();			
			if(name.equals("route_id")){
				routeId=jsonReader.nextString();
				routeId = Uploader.agencyprefix + routeId;
			}else if(name.equals("trip_id")){
				tripId = jsonReader.nextString();
				tripId = Uploader.agencyprefix + tripId;
			}else if(name.equals("service_id")){
				serviceId = jsonReader.nextString();
				serviceId = Uploader.agencyprefix + serviceId;
			}else if(name.equals("trip_headsign")){
				headsign = jsonReader.nextString();
			}else if(name.equals("trip_short_name")){
				shortName = jsonReader.nextString();
			}else if(name.equals("direction_id")){
				directionId = jsonReader.nextString();
			}else{
				jsonReader.nextString();
			}
		}
		TripModel model = new TripModel(tripId,routeId,serviceId,headsign, shortName, directionId);
		this.tripModels.add(model);
		jsonReader.endObject();
	}
}
