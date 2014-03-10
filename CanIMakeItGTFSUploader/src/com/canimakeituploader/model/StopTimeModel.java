package com.canimakeituploader.model;

public class StopTimeModel {
	private String trip_id;
	private String arrival_time;
	private String departure_time;
	private String stop_id;
	private String stop_sequence;
	
	public StopTimeModel(String tripId,String stopId,String arrivalTime, String departureTime,String stopSequence){
		this.trip_id = tripId;
		this.stop_id = stopId;
		this.arrival_time = arrivalTime;
		this.departure_time = departureTime;
		this.stop_sequence = stopSequence;
	}
	
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getStop_id() {
		return stop_id;
	}
	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getStop_sequence() {
		return stop_sequence;
	}
	public void setStop_sequence(String stop_sequence) {
		this.stop_sequence = stop_sequence;
	}
	public String toString(){
		return "STOP TIME: " + this.trip_id + " " + this.stop_id + " " + this.departure_time + " " + this.arrival_time + " " + this.stop_sequence;
	}
}
