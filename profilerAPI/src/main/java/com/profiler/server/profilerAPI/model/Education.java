package com.profiler.server.profilerAPI.model;

import java.util.ArrayList;

public class Education {
	
    private String institution;
	private String city;
	private String state;
	private String degree;
	private String fieldOfStudy;
	private String graduationDate;
	private String descriptionEdu;
	private ArrayList<String> awards;
	
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
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
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getDescriptionEdu() {
		return descriptionEdu;
	}
	public void setDescriptionEdu(String descriptionEdu) {
		this.descriptionEdu = descriptionEdu;
	}
	public ArrayList<String> getAwards() {
		return awards;
	}
	public void setAwards(ArrayList<String> awards) {
		this.awards = awards;
	}
	@Override
	public String toString() {
		return "Education [institution=" + institution + ", city=" + city + ", state=" + state + ", degree=" + degree
				+ ", fieldOfStudy=" + fieldOfStudy + ", graduationDate=" + graduationDate + ", descriptionEdu="
				+ descriptionEdu + ", awards=" + awards + "]";
	}
}
