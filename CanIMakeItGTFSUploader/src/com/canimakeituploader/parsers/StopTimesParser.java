package com.canimakeituploader.parsers;

import com.canimakeituploader.Util;
import com.google.gson.stream.JsonReader;

public class StopTimesParser  implements Parser {
	public void parse(JsonReader jsonReader) throws Exception{
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readStopTime(jsonReader);			
		}
		jsonReader.endArray();
		return;
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
		while(jsonReader.hasNext()){			
			Util.consumeToken(jsonReader);	
		}
		jsonReader.endObject();
	}
}
