package com.canimakeituploader.parsers;

import com.canimakeituploader.Util;
import com.google.gson.stream.JsonReader;

public class AgencyParser implements Parser{
	
	public void parse(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			Util.consumeToken(jsonReader);			
		}
		jsonReader.endObject();
		return;
	}
}
