package com.canimakeituploader.parsers;

import com.canimakeituploader.Util;
import com.google.gson.stream.JsonReader;

public class TripsParser  implements Parser{
	public void parse(JsonReader jsonReader) throws Exception{
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
		while(jsonReader.hasNext()){			
			Util.consumeToken(jsonReader);	
		}
		jsonReader.endObject();
	}
}
