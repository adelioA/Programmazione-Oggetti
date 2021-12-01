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
	
	private JSONObject requestURL(String url) throws JSONException, Unable2ReachAPI {
			OkHttpClient client = new OkHttpClient.Builder()
					.build();
			
			Request request = new Request.Builder()
					.url(url)
					.addHeader("Content-Type", "application/json")
					.addHeader("Authorization", "Token cd8319f3089e09bdef7995a5268db2c9fa5d0356")
					.get()
					.build();
			
			JSONObject apiResult = null;
			
			try {
				Response response = client.newCall(request).execute();
				apiResult = new JSONObject(response.body().string());
			} catch (NullPointerException | IOException e) {
				throw new Unable2ReachAPI();
			}
			
			return apiResult;
	}
	
	public LinkedList<JobRecord> getFullAPIResponse(String startingUrl) throws JSONException, Unable2ReachAPI {
		JSONArray apiResponse = new JSONArray();
		LinkedList<JobRecord> records = new LinkedList<>();
		
		JSONObject response = null;
		JSONArray pageResults = null;
		
		do {
			if (response == null)
				response = this.requestURL(startingUrl);
			else
				response = this.requestURL(response.getString("next"));
			
			pageResults = (response.isNull("results")) ? new JSONArray() : response.getJSONArray("results");
						
			for(int i = 0; i < pageResults.length(); ++i)
				if(!pageResults.getJSONObject(i).isNull("location"))
					apiResponse.put(pageResults.getJSONObject(i));
			
		} while(!response.isNull("next"));
		
		for(int i = 0; i < apiResponse.length(); ++i)
			records.add(JobRecord.fromJSON(apiResponse.getJSONObject(i)));
		
		return records;
	}

	public LinkedList<JobRecord> getFullAPIResponse() throws JSONException, Unable2ReachAPI {
		RequestBuilder builder = new RequestBuilder();
		return getFullAPIResponse(builder.build());
	}
	
	
	
	public static class RequestBuilder {
		private final String baseLink = "https://findwork.dev/api/jobs/";
		private String[] keywords = {  };
		private Boolean remote;
		private String employment; // "full time" o "contract"
		private String location;
		
		
		
		public RequestBuilder() {
			this.remote = null;
			this.employment = "";
			this.location = "";
		}
		
		public RequestBuilder location(String location) {
			this.location = location;
			return this;
		}
		
		public RequestBuilder employment(String type) {
			if((type != null) && (type.equals("full time") || type.equals("contract")))
				this.employment = type;
			return this;
		}
		
		public RequestBuilder remote(Boolean remote) {
			this.remote = remote;
			return this;
		}
		
		public RequestBuilder keywords(String... keywords) {
			this.keywords = keywords;
			return this;
		}
		
		public String build() {
			String completeLink = this.baseLink + "?";

			completeLink += "location=" + this.location + "&";
			completeLink += "remote=" + ((remote == null) ? "" : remote) + "&";
			completeLink += "employment_type=" + ((employment == null) ? "" : employment) + "&";
			
			String keywordsJoin = "";
			if (keywords != null)
				for(String keyword: keywords)
					keywordsJoin += keyword + "+";
			
			completeLink += "search=" + keywordsJoin;
			
			return completeLink;
		}
	}
}
