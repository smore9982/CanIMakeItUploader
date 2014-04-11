package com.canimakeituploader.parsers.njt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.canimakeituploader.Uploader;
import com.canimakeituploader.Util;
import com.canimakeituploader.dao.RoutesDao;
import com.canimakeituploader.dao.StopsDao;
import com.canimakeituploader.model.RouteModel;
import com.canimakeituploader.model.TripModel;
import com.google.gson.stream.JsonReader;

public class RoutesParser implements Parser {
	List<RouteModel> routes = new ArrayList<RouteModel>();
	public void parse(File file) throws Exception{
		readRoutes(file);
		RoutesDao dao = new RoutesDao();
		dao.save(routes);
		return;
	}
	
	private void readRoutes(File file) throws Exception {
		InputStream is = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//Skip the first line
		String line = bufferedReader.readLine();	
		line = bufferedReader.readLine();
		while(line != null && line.length() > 0){
			line = line.replace("\"", "");
			String[] tokens = line.split(",");
			if(tokens.length >=5){
				String routeId = tokens[0];
				routeId = Uploader.agencyprefix + routeId;
				String agencyId = tokens[1];
				String routeShortName = tokens[2];
				String routeLongName = tokens[3];
				String routeType = tokens[4];
				RouteModel model = new RouteModel(routeId,routeShortName,routeLongName,routeType);
				this.routes.add(model);
			}
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return;
	}
}
