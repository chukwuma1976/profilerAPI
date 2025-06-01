package com.profiler.server.profilerAPI.model;

import java.util.ArrayList;

public class Experience {

	private String employer ;
	private String title;
	private String city;
	private String state;
    private String startDate;
    private String endDate;
    private ArrayList<String> description;

	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ArrayList<String> getDescription() {
		return description;
	}
	public void setDescription(ArrayList<String> description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Experience [employer=" + employer + ", title=" + title + ", city=" + city + ", state=" + state
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description + "]";
	}
}
