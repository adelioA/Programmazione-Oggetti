package com.antonininorandrea.ProgettoFindWork.Models;

import java.util.Date;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JobRecord {
	private long id; // 'id'
	private String location; // 'location'; Null se remoto
	private boolean remote; // 'remote'
	private String employment; // 'employment-type'; Null se part-time (o remote?)
	private String role; // 'role'
	private String link; // 'url'
	private Date postingDate; // 'date_pusted'
	private LinkedList<String> keywords; // 'keywords'
	
	
	// TODO fare costruttore
	
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
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	
	
	
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
		
		if (json.isNull("employment_type"))
			record.setEmployment(null);
		else
			record.setEmployment(json.getString("employment_type").toLowerCase());
		
		record.setRole(json.getString("role").toLowerCase());
		record.setLink(json.getString("url"));
		//record.setPostingDate(new Date(json.getString("date_posted")));
		record.setKeywords(keywords);
		
		return record;
	}
}
