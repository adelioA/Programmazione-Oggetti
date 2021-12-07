package com.univpm.ProgettoFindWork.Models;

import java.util.Date;


/**
 * @class FilterRequest
 * 
 * Classe che serve per contenere i parametri della richiesta.
 * @implNote Da notare che non è necessario un construttore, in quanto Spring convertirà direttamente da JSON ad oggetto di classe.
 */
public class FilterRequest {
	private String[] locations;
	private String[] keywords;
	private Boolean remote;
	private String employment;
	private String role;
	private Integer minKeywords;
	private Integer maxKeywords;
	private boolean includeStatistics;
	
	/**
	 * Getters e Setters
	 */
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
	public boolean isStatisticsIncluded() {
		return includeStatistics;
	}
	public void setIncludeStatistics(boolean includeStatistics) {
		this.includeStatistics = includeStatistics;
	}
}
