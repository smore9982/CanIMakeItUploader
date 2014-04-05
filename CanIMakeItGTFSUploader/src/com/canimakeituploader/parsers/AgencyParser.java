package com.canimakeituploader.parsers;

import com.canimakeituploader.dao.AgencyDao;
import com.canimakeituploader.model.AgencyModel;
import com.google.gson.stream.JsonReader;

public class AgencyParser implements Parser{
	
	public void parse(JsonReader jsonReader) throws Exception{
		AgencyModel model = readAgencyInfor(jsonReader);
		if(model != null){
			AgencyDao dao = new AgencyDao();
			dao.save(model);
		}
		return;
	}
	
	public AgencyModel readAgencyInfor(JsonReader jsonReader) throws Exception{
		jsonReader.beginObject();
		String agencyId = "";
		String agencyName = "";
		String agencyUrl = "";
		String agencyTimeZone = "";
		String agencyLang = "";
		String agencyPhone = "";
		while(jsonReader.hasNext()){
			String name = jsonReader.nextName();			
			if(name.equals("agency_id")){
				agencyId=jsonReader.nextString();
			}else if(name.equals("agency_name")){
				agencyName = jsonReader.nextString();
			}else if(name.equals("agency_url")){
				agencyUrl = jsonReader.nextString();
			}else if(name.equals("agency_timezone")){
				agencyTimeZone = jsonReader.nextString();
			}else if(name.equals("agency_lang")){
				agencyLang = jsonReader.nextString();
			}else if(name.equals("agency_phone")){
				agencyPhone = jsonReader.nextString();
			}
		}
		jsonReader.endObject();
		AgencyModel model = new AgencyModel(agencyId, agencyName, agencyUrl, agencyTimeZone, agencyPhone);
		return model;
		
	}
}
