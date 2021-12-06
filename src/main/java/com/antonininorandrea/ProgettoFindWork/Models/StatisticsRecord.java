package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @class	StatisticsRecord
 * Classe contenente le statistiche di una città
 */
public class StatisticsRecord {
	private String location;
	private double fullTimePercentage;
	private double partTimePercentage;
	private double remotePercentage;
	private LinkedList<String> topRoles;
	private int minKeywords;
	private int maxKeywords;
	private LinkedList<KeywordStatisticsRecord> keywordsStatistics;
	
	
	protected StatisticsRecord(String location) {
		this(location, 0, 0, 0, new LinkedList<String>(), Integer.MAX_VALUE, 0, new LinkedList<>());
	}
	
	protected StatisticsRecord(String location, double fullTimePercentage, double partTimePercentage,
			double remotePercentage, LinkedList<String> topRoles, int minKeywords, int maxKeywords, LinkedList<KeywordStatisticsRecord> keywordStatistics) {
		this.setLocation(location);
		this.setFullTimePercentage(fullTimePercentage);
		this.setPartTimePercentage(partTimePercentage);
		this.setRemotePercentage(remotePercentage);
		this.setTopRoles(topRoles);
		this.setMinKeywords(minKeywords);
		this.setMaxKeywords(maxKeywords);
		this.setKeywordsStatistics(keywordStatistics);
	}
	
	
	/**
	 * Getters e Setters
	 */
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getFullTimePercentage() {
		return fullTimePercentage;
	}
	public void setFullTimePercentage(double fullTimePercentage) {
		this.fullTimePercentage = fullTimePercentage;
	}
	public double getPartTimePercentage() {
		return partTimePercentage;
	}
	public void setPartTimePercentage(double partTimePercentage) {
		this.partTimePercentage = partTimePercentage;
	}
	public double getRemotePercentage() {
		return remotePercentage;
	}
	public void setRemotePercentage(double remotePercentage) {
		this.remotePercentage = remotePercentage;
	}
	public LinkedList<String> getTopRoles() {
		return topRoles;
	}
	public void setTopRoles(LinkedList<String> topRoles) {
		this.topRoles = topRoles;
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
	public LinkedList<KeywordStatisticsRecord> getKeywordsStatistics() {
		return keywordsStatistics;
	}
	public void setKeywordsStatistics(LinkedList<KeywordStatisticsRecord> keywordsStatistics) {
		this.keywordsStatistics = keywordsStatistics;
	}
	
	
	
	/**
	 * @param	location	Città di riferimento delle statistiche
	 * @param	collection	Lista di risultati (già passati per città)
	 * @return	Statistiche della città
	 */
	public static StatisticsRecord getStatisticsFromCollection(String location, LinkedList<JobRecord> collection) {
		StatisticsRecord stats = new StatisticsRecord(location);
		
		int remoteCounter = 0;
		int fullTimeCounter = 0;
		LinkedList<String> keywords = new LinkedList<>();
		LinkedList<String> roles = new LinkedList<>(); // Top roles
		
		HashMap<String, Integer> rolesMap = new HashMap<>();
		
		for(int i = 0; i < collection.size(); ++i) {
			JobRecord item = collection.get(i);
			
			if (item.isRemote())
				++remoteCounter;
			if (item.getEmployment().equals("full time"))
				++fullTimeCounter;
			if (item.getKeywords().size() > stats.getMaxKeywords())
				stats.setMaxKeywords(item.getKeywords().size());
			if (item.getKeywords().size() < stats.getMinKeywords())
				stats.setMinKeywords(item.getKeywords().size());
			
			if (rolesMap.containsKey(item.getRole()))
				rolesMap.put(item.getRole(), rolesMap.get(item.getRole()) + 1);
			else
				rolesMap.put(item.getRole(), 1);
			
			// List Keywords
			for(int j = 0; j < item.getKeywords().size(); ++j) {
				if (!keywords.contains(item.getKeywords().get(j)))
					keywords.add(item.getKeywords().get(j));
			}
			
		}
		
		for(int i = 0, topRolesCount = (rolesMap.size() > 5) ? 5 : rolesMap.size(); i < topRolesCount; ++i) {
			//Set<String, Integer> maxCountRole = new Set<>("", 0);
			String maxRoleName = "";
			int maxRoleCount = 0;
			
			for(Map.Entry<String, Integer> entry: rolesMap.entrySet())
				if (entry.getValue() >= maxRoleCount) {
					maxRoleName = entry.getKey();
					maxRoleCount = entry.getValue();
				}
			
			roles.add(maxRoleName);
			rolesMap.remove(maxRoleName);
		}
		
		
		stats.setRemotePercentage((double) remoteCounter / (double) collection.size());
		stats.setFullTimePercentage((double) fullTimeCounter / (double) collection.size());
		stats.setPartTimePercentage((double)(collection.size() - fullTimeCounter) / (double) collection.size());
		stats.setTopRoles(roles);
		for(int i = 0; i < keywords.size(); ++i) 
			stats.getKeywordsStatistics().add(KeywordStatisticsRecord.getKeywordStatisticsFromCollection(keywords.get(i), collection));
		
		return stats;
	}
}
