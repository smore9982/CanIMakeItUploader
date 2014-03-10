package com.canimakeituploader.model;

public class TripModel {
	private String route_id;
	private String service_id;
	private String trip_id;
	private String trip_headsign;
	private String trip_short_name;
	private String direction_id;
	
	public TripModel(String tripId, String routeId, String serviceId, String tripHeadSign, String tripShortName, String directionId){
		this.trip_id = tripId;
		this.route_id = routeId;
		this.service_id = serviceId;
		this.trip_headsign = tripHeadSign;
		this.trip_short_name = tripShortName;
		this.direction_id = directionId;
		
	}
	public String getDirection_id() {
		return direction_id;
	}
	public void setDirection_id(String direction_id) {
		this.direction_id = direction_id;
	}
	public String getTrip_short_name() {
		return trip_short_name;
	}
	public void setTrip_short_name(String trip_short_name) {
		this.trip_short_name = trip_short_name;
	}
	public String getTrip_headsign() {
		return trip_headsign;
	}
	public void setTrip_headsign(String trip_headsign) {
		this.trip_headsign = trip_headsign;
	}
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String toString(){
		return "TRIP: " + this.trip_id + " " + this.route_id + " " + this.service_id + " " + this.trip_headsign + " " + this.trip_short_name + " " + this.direction_id;
	}
	
	
}
