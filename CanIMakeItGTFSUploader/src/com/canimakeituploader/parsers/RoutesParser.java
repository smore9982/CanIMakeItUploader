package com.canimakeituploader.parsers;

import java.util.ArrayList;
import java.util.List;

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
		for(int i=0;i<routes.size();i++){
			System.out.println(routes.get(i));
		}
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
			if(name.equals("stop_id")){
				routeId=jsonReader.nextString();
			}else if(name.equals("route_short_name")){
				routeShortName = jsonReader.nextString();
			}else if(name.equals("route_long_name")){
				routeLongName = jsonReader.nextString();
			}else if(name.equals("route_type")){
				routeType = jsonReader.nextString();
			}else if(name.equals("route_color")){
				routeType = jsonReader.nextString();
			}else if(name.equals("route_text_color")){
				routeType = jsonReader.nextString();
			}
		}
		RouteModel model = new RouteModel(routeId,routeShortName,routeLongName,routeType);
		routes.add(model);
		jsonReader.endObject();
	}
}
