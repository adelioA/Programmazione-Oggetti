package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.LinkedList;

public class SearchResult {
	private LinkedList<JobRecord> records;
	private LinkedList<StatisticsRecord> statistics;
	
	
	
	public SearchResult(LinkedList<JobRecord> records, LinkedList<StatisticsRecord> statistics) {
		this.setRecords(records);
		this.setStatistics(statistics);
	}
	
	
	
	public LinkedList<JobRecord> getRecords() {
		return records;
	}
	public void setRecords(LinkedList<JobRecord> records) {
		this.records = records;
	}
	public LinkedList<StatisticsRecord> getStatistics() {
		return statistics;
	}
	public void setStatistics(LinkedList<StatisticsRecord> statistics) {
		this.statistics = statistics;
	}
}
