package com.abir.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Employee {
	
	private int id;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String mobile;
	
	private String github;
	
	
	private String linkedin;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private List<Education> educations;
	
	private List<Skill> skills;
	
	private List<Project> projects;
	
	private List<Achievement> achievements;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, String firstName, String lastName, List<Education> educations, List<Skill> skills,
			List<Project> projects, List<Achievement> achievements) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.educations = educations;
		this.skills = skills;
		this.projects = projects;
		this.achievements = achievements;
	}
	public Employee(int id, String firstName, String lastName, String mobile, String github, String linkedin,
			String email, String password, String address, List<Education> educations, List<Skill> skills, List<Project> projects,
			List<Achievement> achievements) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.github = github;
		this.linkedin = linkedin;
		this.email = email;
		this.password = password;
		this.address = address;
		this.educations = educations;
		this.skills = skills;
		this.projects = projects;
		this.achievements = achievements;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<Education> getEducations() {
		return educations;
	}
	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<Achievement> getAchievements() {
		return achievements;
	}
	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFullName() {
		return firstName+" "+lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
}
