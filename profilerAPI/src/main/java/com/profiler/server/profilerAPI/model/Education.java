package com.profiler.server.profilerAPI.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Component
@Scope("prototype")
@Table(name = "Education")
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String institution;
	private String city;
	private String state;
	private String degree;
	private String fieldOfStudy;
	private String graduationDate;
	private String descriptionEdu;
	private List<String> awards;
    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = true)
    private Resume resume;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public List<String> getAwards() {
		return awards;
	}
	public void setAwards(List<String> awards) {
		this.awards = awards;
	}
//	public Resume getResume() {
//		return resume;
//	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
//	public Long getResumeId() {
//		return this.resume_id;
//	}
//	public void setResumeId(Long resumeId) {
//		this.resume_id = resumeId;
//	}
	@Override
	public String toString() {
		return "Education [institution=" + institution + ", city=" + city + ", state=" + state + ", degree=" + degree
				+ ", fieldOfStudy=" + fieldOfStudy + ", graduationDate=" + graduationDate + ", descriptionEdu="
				+ descriptionEdu + ", awards=" + awards + "]";
	}
}
