package com.canimakeituploader;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Util {
	
	/*
	 * Use this when you don't care about the token and want the parser to skip.
	 */
	public static void consumeToken(JsonReader reader) throws Exception{
		JsonToken token = reader.peek();		
		switch (token) {        
        case NAME:
            String name = reader.nextName();            
            break;
        case STRING:
            String s = reader.nextString();
            break;
        case NUMBER:
            String n = reader.nextString();            
            break;
        case BOOLEAN:
            boolean b = reader.nextBoolean();
            break;
        case NULL:
            reader.nextNull();
            break;
        case BEGIN_ARRAY:
            reader.beginArray();
            break;
        case END_ARRAY:
            reader.endArray();
            break;
        case BEGIN_OBJECT:
            reader.beginObject();
            break;
        case END_OBJECT:
            reader.endObject();
            break;		
    	case END_DOCUMENT:  
    		break;
    	}
	}
}
