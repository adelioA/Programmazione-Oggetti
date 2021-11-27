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
	
	public JSONArray getAPIResult() throws JSONException, Unable2ReachAPI {
		OkHttpClient client = new OkHttpClient.Builder()
				.build();
		
		Request request = new Request.Builder()
				.url("https://findwork.dev/api/jobs/")
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
			throw new JsonParsingException();
		}
		
		return jobs;
	}
	
	
}
