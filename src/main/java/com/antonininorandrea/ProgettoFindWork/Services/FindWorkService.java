package com.antonininorandrea.ProgettoFindWork.Services;

import java.io.IOException;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.antonininorandrea.ProgettoFindWork.Models.JobRecord;
import com.antonininorandrea.ProgettoFindWork.Models.Exceptions.JsonParsingException;
import com.antonininorandrea.ProgettoFindWork.Models.Exceptions.Unable2ReachAPI;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FindWorkService {	
	// HOST:			https://findwork.dev/api/jobs/?location=london&search=react&sort_by=relevance
	// API_KEY:			cd8319f3089e09bdef7995a5268db2c9fa5d0356
	
	public JSONArray getAPIResult(String location) throws JSONException, Unable2ReachAPI {
		OkHttpClient client = new OkHttpClient.Builder()
				.build();
		
		Request request = new Request.Builder()
				.url("https://findwork.dev/api/jobs/?location=" + location)
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "Token cd8319f3089e09bdef7995a5268db2c9fa5d0356")
				.get()
				.build();
		
		JSONArray apiResult = null;
		
		try {
			Response response = client.newCall(request).execute();
			JSONObject result = new JSONObject(response.body().string());
			apiResult = result.getJSONArray("results");
		} catch (NullPointerException | IOException e) {
			throw new Unable2ReachAPI();
		}
		
		return apiResult;
	}
	
	public JSONArray getAPIResult() throws JSONException, Unable2ReachAPI {
		return getAPIResult("");
	}

	public LinkedList<JobRecord> getAllJobs() throws Unable2ReachAPI, JsonParsingException {
		LinkedList<JobRecord> jobs = new LinkedList<>();
		JSONArray records = null;
		
		
		try {
			records = this.getAPIResult();
			
			for(int i = 0; i < records.length(); ++i) {
				JSONObject item = records.getJSONObject(i);
				jobs.add(JobRecord.fromJSON(item));
			}
			
		} catch (JSONException e) {
			throw new JsonParsingException(e.getMessage());
		}
		
		return jobs;
	}
	
	public LinkedList<JobRecord> getAllJobsByLocations(String... locations) throws Unable2ReachAPI, JsonParsingException {
		LinkedList<JobRecord> jobs = new LinkedList<>();
		
		JSONArray records = null;
		
		try {
			
			for(String location: locations) {
				records = this.getAPIResult(location);
				
				for(int i = 0; i < records.length(); ++i) {
					JSONObject item = records.getJSONObject(i);
					item.put("location", location.toUpperCase());
					jobs.add(JobRecord.fromJSON(item));
				}
			}
			
		} catch (JSONException e) {
			throw new JsonParsingException(e.getMessage());
		}
		
		return jobs;
	}
	
	public LinkedList<JobRecord> filterByKeyword(LinkedList<JobRecord> collection, String keyword) {
		LinkedList<JobRecord> filteredCollection = new LinkedList<>();
		
		// Per ogni record
		for(int i = 0; i < collection.size(); ++i) {
			// Prendo la lista di keyword
			LinkedList<String> recordKeywords = collection.get(i).getKeywords();
			
			// Cerco corrispondenza
			if (recordKeywords.contains(keyword.toLowerCase())) {
				filteredCollection.add(collection.get(i));		// Se la trovo aggiungo alla lista filtrata
			}
		}
		
		return filteredCollection;
	}
}
