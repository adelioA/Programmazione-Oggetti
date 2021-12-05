package com.antonininorandrea.ProgettoFindWork.Services;

import java.io.IOException;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.antonininorandrea.ProgettoFindWork.Models.JobRecord;
import com.antonininorandrea.ProgettoFindWork.Models.Exceptions.Unable2ReachAPI;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @class	FindWorkService
 * Classe per fornire i servizi di accesso all'API di findword.dev
 */
public class FindWorkService {	
	// HOST:			https://findwork.dev/api/jobs/?location=london&search=react&sort_by=relevance
	// API_KEY:			cd8319f3089e09bdef7995a5268db2c9fa5d0356
	
	/**
	 * @param	url		Url della richiesta
	 * @return	L'oggetto JSON ottenuto dall'API
	 * 
	 * @throws JSONException
	 * @throws Unable2ReachAPI
	 */
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
	
	/**
	 * @param	startingUrl	Url di partenza, ovvero della prima richiesta.
	 * @return	Lista di lavori trovati.
	 * 
	 * @throws JSONException
	 * @throws Unable2ReachAPI
	 * 
	 * @implNote	Si è utilizzato il nome startingUrl in quanto findwork.dev raggruppa i risultati in pagine contenenti massimo 100 di essi.
	 * 				Per ottenerli tutti è necessario continuare a fare chiamate ai link contenuti nel campo "next" della risposta.
	 * 				Tuttavia, questo comporta una non indifferente lentezza nel restituire un risultato completo.
	 */
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

	/**
	 * @return	Come il metodo precedente, ma con tutti i risultati di findwork
	 * 
	 * @throws JSONException
	 * @throws Unable2ReachAPI
	 * 
	 * @implNote	In realtà non sono propriamente tutti i risultati, siccome findwork limita le richieste a 60 al minuto.
	 * @deprecated	Metodo implementato ma non utilizzato. Tuttavia non si sa mai.
	 */
	public LinkedList<JobRecord> getFullAPIResponse() throws JSONException, Unable2ReachAPI {
		RequestBuilder builder = new RequestBuilder();
		return getFullAPIResponse(builder.build());
	}
	
	
	
	/**
	 * @class	RequestBuilder
	 * Classe statica per costruire i link delle chiamate API.
	 * 
	 * @implNote	Nello specifico serve per sfruttare i filtri interni all'API se richiesti
	 */
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
		
		
		
		/**
		 * @param	location	Il luogo dove cercare l'impiego
		 * @return	L'oggetto builder
		 */
		public RequestBuilder location(String location) {
			this.location = location;
			return this;
		}
		
		/**
		 * @param	type	Il tipo di impiego che si vuole trovarte. "full time" per i lavori Full Time; "contract" per quelli a Contratto/Part-Time
		 * @return	L'oggetto builder
		 */
		public RequestBuilder employment(String type) {
			if((type != null) && (type.equals("full time") || type.equals("contract")))
				this.employment = type;
			return this;
		}
		
		/**
		 * @param	remote	Sepifica se bisogna filtrare solo i lavori da remoto/in presenza. Il valore null include entrambi.
		 * @return	L'oggetto builder
		 */
		public RequestBuilder remote(Boolean remote) {
			this.remote = remote;
			return this;
		}
		
		/**
		 * @param	keywords	Lista di keywords da includere nella ricerca
		 * @return	L'oggetto builder
		 */
		public RequestBuilder keywords(String... keywords) {
			this.keywords = keywords;
			return this;
		}
		
		/**
		 * Metodo per ottenere il link su cui effettuare la richiesta.
		 * @return	Il link creato dalle impostazioni del builder
		 */
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
