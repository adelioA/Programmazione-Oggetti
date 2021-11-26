package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.LinkedList;

public class JobRecord {
	private long id; // 'id'
	private String location; // 'location'; Null se remoto
	private boolean remote; // 'remote'
	private boolean fullTime; // 'employment-type'; Null se part-time
	private String role; // 'role
	private String link; // 'url'
	private LinkedList<String> keywords; // 'keywords'
	
	
	// TODO fare costruttore
	
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
