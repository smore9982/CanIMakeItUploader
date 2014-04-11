package com.canimakeituploader.parsers.njt;

import java.io.File;

import com.google.gson.stream.JsonReader;

public interface Parser {
	public void parse(File file) throws Exception;
}
