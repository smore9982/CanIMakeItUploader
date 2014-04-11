package com.canimakeituploader.parsers.njt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.canimakeituploader.dao.AgencyDao;
import com.canimakeituploader.model.AgencyModel;
import com.google.gson.stream.JsonReader;

public class AgencyParser implements Parser{
	
	public void parse(File file) throws Exception{
		AgencyModel model = readAgencyInfor(file);
		if(model != null){
			AgencyDao dao = new AgencyDao();
			dao.save(model);
		}
		return;
	}
	
	public AgencyModel readAgencyInfor(File file) throws Exception{
		InputStream is = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//Skip the first line
		String line = bufferedReader.readLine();	
		
		line = bufferedReader.readLine();
		line = line.replace("\"", "");

		String[] tokens = line.split(",");
		String agencyId = tokens[0];
		String agencyName = tokens[1];
		String agencyUrl = tokens[2];
		String agecnyTimeZone = tokens[3];
		bufferedReader.close();
		AgencyModel model = new AgencyModel(agencyId,agencyName,agencyUrl,agecnyTimeZone,"");
		return model;
	}
}
