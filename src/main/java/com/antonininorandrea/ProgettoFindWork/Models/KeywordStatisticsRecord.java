package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.LinkedList;

public class KeywordStatisticsRecord {
	private String keyword;
	private double percentageKeyword; // [0, 1]
	private double percentageRemote; // [0, 1]
	private double percentageFullTime; // [0, 1]
	
	
	protected KeywordStatisticsRecord(String keyword) {
		this(keyword, 0, 0, 0);
	}
	
	protected KeywordStatisticsRecord(String keyword, double percentageKeyword, double percentageRemote,
			double percentageFullTime) {
		this.setKeyword(keyword);
		this.setPercentageKeyword(percentageKeyword);
		this.setPercentageRemote(percentageRemote);
		this.setPercentageFullTime(percentageFullTime);
	}
	
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public double getPercentualeKeyword() {
		return percentageKeyword;
	}
	public void setPercentageKeyword(double percentualeKeyword) {
		this.percentageKeyword = percentualeKeyword;
	}
	
	public double getPercentageRemote() {
		return percentageRemote;
	}
	public void setPercentageRemote(double percentageRemote) {
		this.percentageRemote = percentageRemote;
	}
	
	public double getPercentageFullTime() {
		return percentageFullTime;
	}
	public void setPercentageFullTime(double percentageFullTime) {
		this.percentageFullTime = percentageFullTime;
	}
	
	
	
	public static KeywordStatisticsRecord getKeywordStatisticsFromCollection(String keyword, LinkedList<JobRecord> collection) {
		KeywordStatisticsRecord stats = new KeywordStatisticsRecord(keyword);
		
		int keywordCounter = 0;
		int remoteCounter = 0;
		int fullTimeCounter = 0;
		
		for(int i = 0; i < collection.size(); ++i) {
			JobRecord item = collection.get(i);
		
			if (item.getKeywords().contains(keyword)) {
				++keywordCounter;
				if (item.isRemote())
					++remoteCounter;
				if (item.getEmployment().equals("full time"))
					++fullTimeCounter;
			}
		}
		
		stats.setPercentageKeyword(keywordCounter / collection.size());
		stats.setPercentageRemote(remoteCounter / keywordCounter);
		stats.setPercentageFullTime(fullTimeCounter / keywordCounter);
		
		return stats;
	}
}
