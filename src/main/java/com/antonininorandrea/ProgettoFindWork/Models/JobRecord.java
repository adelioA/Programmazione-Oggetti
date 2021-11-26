package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.LinkedList;

public class JobRecord {
	private long id;
	private String location;
	private boolean remote;
	private boolean fullTime;
	private LinkedList<String> languages;
	private String role;
	private String link;
	private LinkedList<String> keywords;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean isRemote() {
		return remote;
	}
	public void setRemote(boolean remote) {
		this.remote = remote;
	}
	
	public boolean isFullTime() {
		return fullTime;
	}
	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}
	
	public LinkedList<String> getLanguages() {
		return languages;
	}
	public void setLanguages(LinkedList<String> languages) {
		this.languages = languages;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public LinkedList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(LinkedList<String> keywords) {
		this.keywords = keywords;
	}	
}
