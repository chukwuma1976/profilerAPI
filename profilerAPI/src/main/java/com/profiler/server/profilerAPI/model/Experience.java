package com.profiler.server.profilerAPI.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
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
@Table(name = "Experience")
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String employer ;
	private String title;
	private String city;
	private String state;
    private String startDate;
    private String endDate;
    private List<String> description;   
    @ManyToOne
    @JoinColumn(name = "resume_id", nullable = true)
    private Resume resume;
    @Column(insertable=false, updatable=false)
    private Long resume_id;

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
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public Long getResumeId() {
		return this.resume_id;
	}
	public void setResumeId(Long resumeId) {
		this.resume_id = resumeId;
	}
	@Override
	public String toString() {
		return "Experience [employer=" + employer + ", title=" + title + ", city=" + city + ", state=" + state
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description + "]";
	}
}
