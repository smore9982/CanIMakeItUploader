package com.canimakeituploader.parsers;

import com.canimakeituploader.Util;
import com.google.gson.stream.JsonReader;

public class CalendarDatesParser  implements Parser{
	public void parse(JsonReader jsonReader) throws Exception{
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readCalendarDate(jsonReader);			
		}
		jsonReader.endArray();
		return;
	}
	
	private void readCalendarDate(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();	
			if(name.equals("calendar_date")){
				readCurrentCalendarDate(jsonReader);
			}
		}
		jsonReader.endObject();
	}
	private void readCurrentCalendarDate(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){			
			Util.consumeToken(jsonReader);	
		}
		jsonReader.endObject();
	}
}
