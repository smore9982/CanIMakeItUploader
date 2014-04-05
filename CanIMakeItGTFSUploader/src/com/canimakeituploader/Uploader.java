package com.canimakeituploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.canimakeituploader.parsers.AgencyParser;
import com.canimakeituploader.parsers.CalendarDatesParser;
import com.canimakeituploader.parsers.FeedParser;
import com.canimakeituploader.parsers.RoutesParser;
import com.canimakeituploader.parsers.ShapesParser;
import com.canimakeituploader.parsers.StopParser;
import com.canimakeituploader.parsers.StopTimesParser;
import com.canimakeituploader.parsers.TripsParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Uploader {
	
	public static void main(String[] args) throws Exception {
		String fileName = args[0];
		String agency = args[1];
		if(agency.equals("LIRR")){
			parseLIRRData(fileName);			
		}else{
			System.out.println("Invalid agency");
		}
	}
	
	
	private static void parseLIRRData(String fileName) throws Exception{
		File f = new File(fileName);
		if(f.exists()){
			InputStream is = new FileInputStream(f);
			InputStreamReader isReader = new InputStreamReader(is,"UTF-8");
			JsonReader jsonReader = new JsonReader(isReader);
			jsonReader.beginObject();
			while(jsonReader.hasNext()){
				try{
					JsonToken token = jsonReader.peek();
					if(token.toString().equals(JsonToken.STRING.toString())){
						jsonReader.nextString();				
					}					
					String name = jsonReader.nextName();										
					if(name.equals("gtfs")){
						System.out.println("Parsing gtfs");
						readLIRRTransitFeeds(jsonReader);					
					}
				}
				catch(Exception e){
					e.printStackTrace();
					break;
				}
			}
			jsonReader.endObject();
		}
	}

	private static void readLIRRTransitFeeds(JsonReader jsonReader) throws Exception {
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();
			System.out.println(name);
			if(name.equals("stops")){				
				StopParser parser = new StopParser();
				parser.parse(jsonReader);
			}else if(name.equals("feed_info")){
				FeedParser parser = new FeedParser();
				parser.parse(jsonReader);
			}else if(name.equals("agency")){
				AgencyParser agencyParser = new AgencyParser();
				agencyParser.parse(jsonReader);
			}else if(name.equals("routes")){			
				RoutesParser parser = new RoutesParser();
				parser.parse(jsonReader);
			}else if(name.equals("shapes")){				
				ShapesParser parser = new ShapesParser();
				parser.parse(jsonReader);
				System.out.println("finished shapes");
			}else if(name.equals("trips")){
				TripsParser parser = new TripsParser();
				parser.parse(jsonReader);
			}else if(name.equals("stop_times")){
				StopTimesParser parser = new StopTimesParser();
				parser.parse(jsonReader);
			}else if(name.equals("calendar_dates")){
				CalendarDatesParser parser = new CalendarDatesParser();
				parser.parse(jsonReader);
			}
		}
		jsonReader.endObject();		
	}
}
