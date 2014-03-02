package com.canimakeituploader.parsers;

import java.io.IOException;

import com.canimakeituploader.Util;
import com.google.gson.stream.JsonReader;

public class RoutesParser implements Parser {
	public void parse(JsonReader jsonReader) throws Exception{
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readRoute(jsonReader);		
		}
		jsonReader.endArray();
		return;
	}
	
	private void readRoute(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();	
			if(name.equals("route")){
				readCurrentRoute(jsonReader);
			}
		}
		jsonReader.endObject();
	}
	private void readCurrentRoute(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){			
			Util.consumeToken(jsonReader);	
		}
		jsonReader.endObject();
	}
}
