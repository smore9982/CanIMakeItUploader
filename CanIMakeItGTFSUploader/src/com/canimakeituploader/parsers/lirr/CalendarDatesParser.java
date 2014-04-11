package com.canimakeituploader.parsers.lirr;

import java.util.ArrayList;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.Util;
import com.canimakeituploader.dao.CalendarDateDao;
import com.canimakeituploader.model.CalendarDateModel;
import com.google.gson.stream.JsonReader;

public class CalendarDatesParser  implements Parser{
	private ArrayList<CalendarDateModel> calendarDateModels = new ArrayList<CalendarDateModel>();
	
	public void parse(JsonReader jsonReader) throws Exception{
		readCalendarDates(jsonReader);
		CalendarDateDao dao = new CalendarDateDao();
		dao.save(calendarDateModels);
	}
	
	private void readCalendarDates(JsonReader jsonReader) throws Exception{
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
		String serviceId="";
	 	String date="";
	 	String exceptionType="";		
		jsonReader.beginObject();
		while(jsonReader.hasNext()){			
			String name = jsonReader.nextName();			
			if(name.equals("service_id")){
				serviceId=jsonReader.nextString();
				serviceId=Uploader.agencyprefix+serviceId;
			}else if(name.equals("date")){
				date = jsonReader.nextString();
			}else if(name.equals("exception_type")){
				exceptionType = jsonReader.nextString();
			}	
		}
		CalendarDateModel model = new CalendarDateModel(serviceId,date,exceptionType);
		calendarDateModels.add(model);
		jsonReader.endObject();
	}
}
