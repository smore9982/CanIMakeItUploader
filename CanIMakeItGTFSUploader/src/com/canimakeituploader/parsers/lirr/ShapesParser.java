package com.canimakeituploader.parsers.lirr;

import com.canimakeituploader.Util;
import com.google.gson.stream.JsonReader;

public class ShapesParser  implements Parser {
	public void parse(JsonReader jsonReader) throws Exception{
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readShape(jsonReader);		
		}
		jsonReader.endArray();
		return;
	}
	
	private void readShape(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();	
			if(name.equals("shape")){
				readCurrentCurrenShape(jsonReader);
			}
		}
		jsonReader.endObject();
	}
	private void readCurrentCurrenShape(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){			
			Util.consumeToken(jsonReader);	
		}
		jsonReader.endObject();
	}
}
