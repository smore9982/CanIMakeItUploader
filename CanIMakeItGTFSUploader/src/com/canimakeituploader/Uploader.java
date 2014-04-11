package com.canimakeituploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Uploader {
	public static String agencyprefix;
	public static void main(String[] args) throws Exception {
		String fileName = args[0];
		String agency = args[1];
		if(agency.equals("LIRR")){
			agencyprefix = "LI_";
			parseLIRRData(fileName);			
		}else if(agency.equals("NJT")){
			agencyprefix = "NJT_";
			try{
				parseNJTData(fileName);
			}catch(Exception e){
				e.printStackTrace();
			}
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
						LIRRUploader.readLIRRTransitFeeds(jsonReader);				
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
	
	private static void parseNJTData(String directory) throws Exception{
		NJTUploader.readNJTTransitFeeds(directory);
	}
}
