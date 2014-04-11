package com.canimakeituploader.parsers.lirr;

import com.canimakeituploader.Util;
import com.google.gson.stream.JsonReader;

public class FeedParser  implements Parser{

	@Override
	public void parse(JsonReader reader) throws Exception {
		reader.beginObject();
		while(reader.hasNext()){
			Util.consumeToken(reader);			
		}
		reader.endObject();
		return;
		
	}

}
