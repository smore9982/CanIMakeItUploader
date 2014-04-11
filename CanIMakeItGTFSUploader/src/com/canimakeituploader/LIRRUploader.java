package com.canimakeituploader;

import com.canimakeituploader.parsers.lirr.AgencyParser;
import com.canimakeituploader.parsers.lirr.CalendarDatesParser;
import com.canimakeituploader.parsers.lirr.FeedParser;
import com.canimakeituploader.parsers.lirr.RoutesParser;
import com.canimakeituploader.parsers.lirr.ShapesParser;
import com.canimakeituploader.parsers.lirr.StopParser;
import com.canimakeituploader.parsers.lirr.StopTimesParser;
import com.canimakeituploader.parsers.lirr.TripsParser;
import com.google.gson.stream.JsonReader;

public class LIRRUploader {
	
	
	public static void readLIRRTransitFeeds(JsonReader jsonReader) throws Exception {
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
