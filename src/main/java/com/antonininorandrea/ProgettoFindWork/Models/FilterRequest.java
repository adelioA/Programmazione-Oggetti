package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.Date;

public class FilterRequest {
	private String[] locations;
	private String[] keywords;
	private Boolean remote;
	private String employment;
	private String role;
	private Integer minKeywords;
	private Integer maxKeywords;
	private boolean includeStatistics;
	//private Date publishingDate;
	private Date firstPosting; // Per filtrare dal
	private Date lastPosting; // Per filtrare fino al
	
	
	
	public FilterRequest(String[] locations, String[] keywords, Boolean remote, String employment, String role,
			Integer minKeywords, Integer maxKeywords, Date publishingDate, boolean includeStatistics) {
		this.setLocations(locations);
		this.setKeywords(keywords);
		this.setRemote(remote);
		this.setEmployment(employment);
		this.setRole(role);
		this.setMinKeywords(minKeywords);
		this.setMaxKeywords(maxKeywords);
		//this.setPublishingDate(publishingDate);
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
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getMinKeywords() {
		return minKeywords;
	}
	public void setMinKeywords(Integer minKeywords) {
		this.minKeywords = minKeywords;
	}
	public Integer getMaxKeywords() {
		return maxKeywords;
	}
	public void setMaxKeywords(Integer maxKeywords) {
		this.maxKeywords = maxKeywords;
	}
	/*public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}*/
	public boolean isStatisticsIncluded() {
		return includeStatistics;
	}
	public void setIncludeStatistics(boolean includeStatistics) {
		this.includeStatistics = includeStatistics;
	}
}
