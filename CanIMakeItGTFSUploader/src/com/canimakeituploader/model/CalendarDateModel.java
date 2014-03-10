package com.canimakeituploader.model;

public class CalendarDateModel {
	private String serviceId;
	private String date;
	private String exceptionType;
	public CalendarDateModel(String serviceId, String date,String exceptionType){
		this.serviceId = serviceId;
		this.date = date;
		this.exceptionType = exceptionType;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public String toString(){
		return "CALENDAR DATE MODEL: " + this.serviceId + " " + this.date + " " + this.exceptionType;
	}
	
	
}
