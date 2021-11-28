package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.LinkedList;

public class SearchResponse {
	private LinkedList<JobRecord> records;
	private LinkedList<LocationStatistics> statistics;
	
	
	
	public SearchResponse(LinkedList<JobRecord> records, LinkedList<LocationStatistics> statistics) {
		this.setRecords(records);
		this.setStatistics(statistics);
	}
	
	
	
	public LinkedList<JobRecord> getRecords() {
		return records;
	}
	public void setRecords(LinkedList<JobRecord> records) {
		this.records = records;
	}
	public LinkedList<LocationStatistics> getStatistics() {
		return statistics;
	}
	public void setStatistics(LinkedList<LocationStatistics> statistics) {
		this.statistics = statistics;
	}
}
