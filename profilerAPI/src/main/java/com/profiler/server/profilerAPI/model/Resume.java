package com.profiler.server.profilerAPI.model;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Component
@Scope("prototype")
@Table(name = "Resume")
public class Resume {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;;
	
	@NotNull(message="You must enter a first name")
	@NotBlank(message="You must enter a first name")
	private String firstName;
	
	@NotNull(message="You must enter a last name")
	@NotBlank(message="You must enter a last name")
	private String lastName;
	
	private String phoneNumber;
	
	@NotNull(message="You must enter an email")
	@NotBlank(message="You must enter an email")
	private String email;
	
	private String linkedIn;
	private String website;
	private String summary;
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Experience> experience;
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Education> education;
    private List<String> skills;
    private String additionalInfo;
    private boolean shareWithOthers;
    private Long userId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public List<Experience> getExperience() {
		return experience;
	}
	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}
	public List<Education> getEducation() {
		return education;
	}
	public void setEducation(List<Education> education) {
		this.education = education;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public boolean isShareWithOthers() {
		return shareWithOthers;
	}
	public void setShareWithOthers(boolean shareWithOthers) {
		this.shareWithOthers = shareWithOthers;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Resume [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", linkedIn=" + linkedIn + ", website=" + website + ", summary="
				+ summary + ", experience=" + experience + ", education=" + education + ", skills=" + skills
				+ ", additionalInfo=" + additionalInfo + ", shareWithOthers=" + shareWithOthers + ", userId=" + userId
				+ "]";
	}	
    
}
