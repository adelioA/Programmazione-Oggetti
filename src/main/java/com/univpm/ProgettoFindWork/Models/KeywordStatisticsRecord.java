package com.univpm.ProgettoFindWork.Models;

import java.util.LinkedList;

/**
 * @class	KeywordStatisticsRecord
 * Classe per definire le statistiche di utilizzo di una keyword
 */
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
	
	
	/**
	 * Getters e Setters
	 */
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
	
	
	
	/**
	 * @param	keyword		La keyword di cui vogliamo calcolare le statistiche di utilizzo
	 * @param	collection	Lista di risultati in cui calcolare le statistiche di utilizzo
	 * @return	Le statistiche di utilizzo di una determinata keyword
	 */
	public static KeywordStatisticsRecord getKeywordStatisticsFromCollection(String keyword, LinkedList<JobRecord> collection) {
		KeywordStatisticsRecord stats = new KeywordStatisticsRecord(keyword);
		
		int keywordCounter = 0;
		int remoteCounter = 0;
		int fullTimeCounter = 0;
		
		// Per ogni record
		for(int i = 0; i < collection.size(); ++i) {
			JobRecord item = collection.get(i);
		
			// Se contiene la keyword
			if (item.getKeywords().contains(keyword)) {
				++keywordCounter;		// Aumento il numero di record contenenti la keyword specioficata
				if (item.isRemote())
					++remoteCounter;	// Se è remoto lo conto per le percentuali remote
				if (item.getEmployment().equals("full time"))
					++fullTimeCounter;	// Se è full time lo conto per le percentuali full time
			}
		}
		
		// Le percentuali vengono calcolate tramite i contatori
		stats.setPercentageKeyword((double) keywordCounter / (double) collection.size());	// Percentuale utilizzo = Utilizzati / Totali
		stats.setPercentageRemote((double) remoteCounter / (double) keywordCounter);		// Percentuale remoti = Remoti / Utilizzati
		stats.setPercentageFullTime((double) fullTimeCounter / (double) keywordCounter);	// Percentuale full time = Full Time / Utilizzati
		
		return stats;
	}
}
