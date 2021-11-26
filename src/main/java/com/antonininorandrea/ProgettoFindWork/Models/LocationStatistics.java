package com.antonininorandrea.ProgettoFindWork.Models;

public class LocationStatistics {
	private String keyword;
	private double percentualeKeyword; // [0, 1]
	private double percentualeRemote; // [0, 1]
	private double percentualeFullTime; // [0, 1]
	
	
	
	public LocationStatistics(String keyword, double percentualeKeyword, double percentualeRemote,
			double percentualeFullTime) {
		this.setKeyword(keyword);
		this.setPercentualeKeyword(percentualeKeyword);
		this.setPercentualeRemote(percentualeRemote);
		this.setPercentualeFullTime(percentualeFullTime);
	}
	
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public double getPercentualeKeyword() {
		return percentualeKeyword;
	}
	public void setPercentualeKeyword(double percentualeKeyword) {
		this.percentualeKeyword = percentualeKeyword;
	}
	
	public double getPercentualeRemote() {
		return percentualeRemote;
	}
	public void setPercentualeRemote(double percentualeRemote) {
		this.percentualeRemote = percentualeRemote;
	}
	
	public double getPercentualeFullTime() {
		return percentualeFullTime;
	}
	public void setPercentualeFullTime(double percentualeFullTime) {
		this.percentualeFullTime = percentualeFullTime;
	}
}
