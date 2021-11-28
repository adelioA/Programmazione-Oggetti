package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.LinkedList;

public class LocationStatistics {
	private String location;
	private LinkedList<KeywordStatistics> keywordStatistics;
	
	
	
	public LocationStatistics(String location, LinkedList<KeywordStatistics> keywordStatistics) {
		this.setLocation(location);
		this.setKeywordStatistics(keywordStatistics);
	}
	
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LinkedList<KeywordStatistics> getKeywordStatistics() {
		return keywordStatistics;
	}
	public void setKeywordStatistics(LinkedList<KeywordStatistics> keywordStatistics) {
		this.keywordStatistics = keywordStatistics;
	}

	
}
