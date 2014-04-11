package com.canimakeituploader.parsers.lirr;

import java.util.ArrayList;
import java.util.List;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.Util;
import com.canimakeituploader.dao.RoutesDao;
import com.canimakeituploader.dao.StopsDao;
import com.canimakeituploader.model.RouteModel;
import com.google.gson.stream.JsonReader;

public class RoutesParser implements Parser {
	List<RouteModel> routes = new ArrayList<RouteModel>();
	public void parse(JsonReader jsonReader) throws Exception{
		readRoutes(jsonReader);
		RoutesDao dao = new RoutesDao();
		dao.save(routes);
		return;
	}
	
	private void readRoutes(JsonReader jsonReader) throws Exception {
		jsonReader.beginArray();
		while(jsonReader.hasNext()){
			readRoute(jsonReader);
		}
		jsonReader.endArray();
	}
	
	private void readRoute(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();	
			if(name.equals("route")){
				readCurrentRoute(jsonReader);
			}
		}
		jsonReader.endObject();
	}
	private void readCurrentRoute(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		String routeId = "";
		String routeShortName = "";
		String routeLongName = "";
		String routeType = "";
		while(jsonReader.hasNext()){			
			String name = jsonReader.nextName();			
			if(name.equals("route_id")){
				routeId=jsonReader.nextString();
				routeId = Uploader.agencyprefix + routeId;
			}else if(name.equals("route_short_name")){
				routeShortName = jsonReader.nextString();
			}else if(name.equals("route_long_name")){
				routeLongName = jsonReader.nextString();
			}else if(name.equals("route_type")){
				routeType = jsonReader.nextString();
			}else if(name.equals("route_color")){
				jsonReader.nextString();
			}else if(name.equals("route_text_color")){
				jsonReader.nextString();
			}
		}
		RouteModel model = new RouteModel(routeId,routeShortName,routeLongName,routeType);
		routes.add(model);
		jsonReader.endObject();
	}
}
