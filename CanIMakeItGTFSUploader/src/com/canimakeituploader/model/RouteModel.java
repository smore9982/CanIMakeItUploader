package com.canimakeituploader.model;

public class RouteModel {
	private String route_id;
	private String route_short_name;
	private String route_long_name;
	private String route_type;
	private String route_color;
	private String route_text_color;
	
	public RouteModel(String id, String shortName, String longName,String type){
		this.route_id = id;
		this.route_short_name = shortName;
		this.route_long_name = longName;
		this.route_type = type;
	}
	
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getRoute_short_name() {
		return route_short_name;
	}
	public void setRoute_short_name(String route_short_name) {
		this.route_short_name = route_short_name;
	}
	public String getRoute_long_name() {
		return route_long_name;
	}
	public void setRoute_long_name(String route_long_name) {
		this.route_long_name = route_long_name;
	}
	public String getRoute_type() {
		return route_type;
	}
	public void setRoute_type(String route_type) {
		this.route_type = route_type;
	}
	public String getRoute_color() {
		return route_color;
	}
	public void setRoute_color(String route_color) {
		this.route_color = route_color;
	}
	public String getRoute_text_color() {
		return route_text_color;
	}
	public void setRoute_text_color(String route_text_color) {
		this.route_text_color = route_text_color;
	}
	
	
	
}
