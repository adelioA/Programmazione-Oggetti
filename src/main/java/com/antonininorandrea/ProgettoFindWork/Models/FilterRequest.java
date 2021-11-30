package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.Date;

public class FilterRequest {
	private String[] locations;
	private String[] keywords;
	private Boolean remote;
	private String employement;
	private String role;
	private int minKeywords;
	private int maxKeywords;
	private Date publishingDate;
	private boolean includeStatistics;
	private Date firstPosting; // Per filtrare dal
	private Date lastPosting; // Per filtrare fino al
	
	
	
	public FilterRequest(String[] locations, String[] keywords, Boolean remote, String employement, String role,
			int minKeywords, int maxKeywords, Date publishingDate, boolean includeStatistics) {
		this.setLocations(locations);
		this.setKeywords(keywords);
		this.setRemote(remote);
		this.setEmployement(employement);
		this.setRole(role);
		this.setMinKeywords(minKeywords);
		this.setMaxKeywords(maxKeywords);
		this.setPublishingDate(publishingDate);
		this.setIncludeStatistics(includeStatistics);
	}
	
	
	
	public String[] getLocations() {
		return locations;
	}
	public void setLocations(String[] locations) {
		this.locations = locations;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public Boolean isRemote() {
		return remote;
	}
	public void setRemote(Boolean remote) {
		this.remote = remote;
	}
	public String getEmployement() {
		return employement;
	}
	public void setEmployement(String employement) {
		this.employement = employement;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getMinKeywords() {
		return minKeywords;
	}
	public void setMinKeywords(int minKeywords) {
		this.minKeywords = minKeywords;
	}
	public int getMaxKeywords() {
		return maxKeywords;
	}
	public void setMaxKeywords(int maxKeywords) {
		this.maxKeywords = maxKeywords;
	}
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public boolean isStatisticsIncluded() {
		return includeStatistics;
	}
	public void setIncludeStatistics(boolean includeStatistics) {
		this.includeStatistics = includeStatistics;
	}
}
