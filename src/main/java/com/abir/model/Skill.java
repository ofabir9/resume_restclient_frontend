package com.abir.model;

import java.util.List;


public class Skill {
	
	
	private int id;
	private String skillType;
	
	private List<String> skillNames;
	
	public Skill() {
		super();
	}
	
	public Skill(String skillType, List<String> skillNames) {
		super();
		this.skillType = skillType;
		this.skillNames = skillNames;
	}
	@Override
	public String toString() {
		return "Skill [skillType=" + skillType + ", skillNames=" + skillNames + "]";
	}
	public String getSkillType() {
		return skillType;
	}
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	public List<String> getSkillNames() {
		return skillNames;
	}
	public void setSkillNames(List<String> skillNames) {
		this.skillNames = skillNames;
	}
		
}
