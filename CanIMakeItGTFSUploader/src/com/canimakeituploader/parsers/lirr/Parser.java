package com.canimakeituploader.parsers.lirr;

import com.google.gson.stream.JsonReader;

public interface Parser {
	public void parse(JsonReader reader) throws Exception;
}
