package com.canimakeituploader.parsers.njt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.dao.CalendarDateDao;
import com.canimakeituploader.model.CalendarDateModel;

public class CalendarDatesParser  implements Parser{
	private ArrayList<CalendarDateModel> calendarDateModels = new ArrayList<CalendarDateModel>();
	
	public void parse(File file) throws Exception{
		readCalendarDates(file);
		CalendarDateDao dao = new CalendarDateDao();
		dao.save(calendarDateModels);
	}
	
	private void readCalendarDates(File file) throws Exception{
		InputStream is = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//Skip the first line
		String line = bufferedReader.readLine();	
		line = bufferedReader.readLine();
		while(line != null && line.length() > 0){
			line = line.replace("\"", "");
			String[] tokens = line.split(",");
			if(tokens.length >=3){
				String serviceId = tokens[0];
				serviceId = Uploader.agencyprefix + serviceId;
				
				String date = tokens[1];
				
				String exceptionType = tokens[2];
				
				CalendarDateModel model = new CalendarDateModel(serviceId,date,exceptionType);
				calendarDateModels.add(model);
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return;
	}
}
