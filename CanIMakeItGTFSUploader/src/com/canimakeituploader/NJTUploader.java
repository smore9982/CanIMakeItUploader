package com.canimakeituploader;

import java.io.File;

import com.canimakeituploader.parsers.njt.AgencyParser;
import com.canimakeituploader.parsers.njt.CalendarDatesParser;
import com.canimakeituploader.parsers.njt.RoutesParser;
import com.canimakeituploader.parsers.njt.StopParser;
import com.canimakeituploader.parsers.njt.StopTimesParser;
import com.canimakeituploader.parsers.njt.TripsParser;

public class NJTUploader {
	public static final String FILENAME_AGENCY = "agency.txt";
	public static final String FILENAME_TRIP = "trips.txt";
	public static final String FILENAME_ROUTES = "routes.txt";
	public static final String FILENAME_STOPTIMES = "stop_times.txt";
	public static final String FILENAME_STOPS = "stops.txt";
	public static final String FILENAME_CALENDERDATES = "calendar_dates.txt";
	
	public static void readNJTTransitFeeds(String directory) throws Exception {
		System.out.println("Starting Read");
		System.out.println("Reading Agency");
		File file = getFile(directory+FILENAME_AGENCY);
		if(file != null){
			AgencyParser parser = new AgencyParser();
			parser.parse(file);
		}
		
		System.out.println("Reading trip");
		file = getFile(directory+FILENAME_TRIP);
		if(file != null){
			TripsParser parser = new TripsParser();
			parser.parse(file);
		}
		
		System.out.println("Reading Routes");
		file = getFile(directory+FILENAME_ROUTES);
		if(file != null){
			RoutesParser parser = new RoutesParser();
			parser.parse(file);
		}
		
		System.out.println("Reading StopTimes");
		file = getFile(directory+FILENAME_STOPTIMES);
		if(file != null){
			StopTimesParser parser = new StopTimesParser();
			parser.parse(file);
		}
		
		System.out.println("Reading stops");
		file = getFile(directory+FILENAME_STOPS);
		if(file != null){
			StopParser parser = new StopParser();
			parser.parse(file);
		}
		
		System.out.println("Reading calendardates");
		file = getFile(directory+FILENAME_CALENDERDATES);
		if(file != null){
			CalendarDatesParser parser = new CalendarDatesParser();
			parser.parse(file);
		}
		System.out.println("Finished Reading");
		
	}
	
	public static File getFile(String fileName){
		System.out.println("File: " +fileName);
		File f = new File(fileName);
		if(f.exists()){
			System.out.println("File exists");
			return f;
		}else{
			System.out.println("File does not exists");
			return null;
		}
	}
}
