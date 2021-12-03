package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.Date;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @class JobRecord
 * 
 * Classe che identifica un risultato ('semplificato') dell'API di FindWork.
 */
public class JobRecord {
	private long id; // 'id'
	private String location; // 'location'; Null se remoto
	private boolean remote; // 'remote'
	private String employment; // 'employment-type'; Null se part-time (o remote?)
	private String role; // 'role'
	private String link; // 'url'
	private LinkedList<String> keywords; // 'keywords'
	
	
	
	/**
	 * Getters e Setters
	 */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean isRemote() {
		return remote;
	}
	public void setRemote(boolean remote) {
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
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public LinkedList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(LinkedList<String> keywords) {
		this.keywords = keywords;
	}
	
	
	
	/**
	 * @param	json L'oggetto JSON contenente un risultato della richiesta.
	 * @return	Il corrispettivo oggetto JobRecord estratto dal JSON
	 * @throws	JSONException Eccezione sollevata in caso di errore di lettura del JSON
	 */
	public static JobRecord fromJSON(JSONObject json) throws JSONException {
		JobRecord record = new JobRecord();
		
		JSONArray keywordJSON = json.getJSONArray("keywords");
		LinkedList<String> keywords = new LinkedList<>();
		
		for (int i = 0; i < keywordJSON.length(); ++i)
			keywords.add(keywordJSON.getString(i).toLowerCase());
		
		record.setId(json.getLong("id"));
		
		if (json.isNull("location"))
			record.setLocation(null);
		else
			record.setLocation(json.getString("location").toUpperCase());
		
		
		record.setRemote(json.getBoolean("remote"));
		
		if (json.isNull("employment_type")) {
			if (json.getString("text").toLowerCase().contains("full time"))
				record.setEmployment("full time");
			else if (json.getString("text").toLowerCase().contains("contract") || json.getString("text").toLowerCase().contains("part time"))
				record.setEmployment("contract");
			else
				record.setEmployment(null);
		}
		else
			record.setEmployment(json.getString("employment_type").toLowerCase());
		
		record.setRole(json.getString("role").toLowerCase());
		record.setLink(json.getString("url"));
		record.setKeywords(keywords);
		
		return record;
	}
}
