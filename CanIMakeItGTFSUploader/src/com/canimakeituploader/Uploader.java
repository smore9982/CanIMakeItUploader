package com.canimakeituploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.canimakeituploader.model.StopModel;
import com.canimakeituploader.parsers.StopParser;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Uploader {
	
	public static void main(String[] args) throws Exception {
		String fileName = args[0];
		File f = new File(fileName);
		if(f.exists()){
			InputStream is = new FileInputStream(f);
			InputStreamReader isReader = new InputStreamReader(is,"UTF-8");
			JsonReader jsonReader = new JsonReader(isReader);
			jsonReader.beginObject();
			while(jsonReader.hasNext()){
				try{
					String name = jsonReader.nextName();
					System.out.println(name);
					if(name.equals("gtfs")){
						readTransitFeeds(jsonReader);					
					}
				}
				catch(Exception e){
					
				}
			}
			jsonReader.endObject();
		}
		System.out.println("It Lives");
	}

	private static void readTransitFeeds(JsonReader jsonReader) throws Exception {
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();
			if(name.equals("stops")){
				StopParser parser = new StopParser();
				parser.parseStopsFeed(jsonReader);
			}
		}
		jsonReader.endObject();		
	}
}
