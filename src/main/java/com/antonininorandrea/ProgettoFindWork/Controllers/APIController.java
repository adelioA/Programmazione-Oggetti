package com.antonininorandrea.ProgettoFindWork.Controllers;

import java.util.LinkedList;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antonininorandrea.ProgettoFindWork.Models.FilterRequest;
import com.antonininorandrea.ProgettoFindWork.Models.JobRecord;
import com.antonininorandrea.ProgettoFindWork.Models.SearchResult;
import com.antonininorandrea.ProgettoFindWork.Models.StatisticsRecord;
import com.antonininorandrea.ProgettoFindWork.Models.Exceptions.Unable2ReachAPI;
import com.antonininorandrea.ProgettoFindWork.Services.FindWorkService;



@RestController
@RequestMapping("api")
@CrossOrigin
public class APIController {

	private FindWorkService apiService;
	
	/**
	 * 
	 */
	public APIController() {
		this.apiService = new FindWorkService();
	}

	/**
	 * @return
	 */
	@GetMapping("suggestions")
	public @ResponseBody String suggestLocations() {
		return "[\"LONDON\", \"BERLIN\", \"PLANO\", \"NEW YORK\", \"PARIS\", \"TOKYO\", \"CHICAGO\", \"BOSTON\", \"SAN FRANCISCO\", \"DENVER\", \"US\"]";
	}
	
	/**
	 * Route che permette di cercare dei lavori tramite l'API di FindWork.
	 * 
	 * @param	filters	Filtri da applicare, mandati tramite body della richiesta GET
	 * @return	Un oggetto SearchResult, contenente la lista di risultati e la lista delle statistiche.
	 */
	@GetMapping("search")
	public @ResponseBody ResponseEntity<SearchResult> search(@RequestBody FilterRequest filters) {
		ResponseEntity<SearchResult> responseEntity = null;
		
		if ((filters.getLocations() == null) || (filters.getLocations().length == 0))
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else {
			LinkedList<JobRecord> records = new LinkedList<>();
			LinkedList<StatisticsRecord> statistics = new LinkedList<>();
			
			// Sfrutto i filtri di findwork.dev
			FindWorkService.RequestBuilder requestBuilder = new FindWorkService.RequestBuilder();
			requestBuilder
				.employment(filters.getEmployment())
				.keywords(filters.getKeywords())
				.remote(filters.isRemote());
				
			
			
			for(int i = 0; i < filters.getLocations().length; ++i) {
				try {
					LinkedList<JobRecord> someRecords = this.apiService.getFullAPIResponse(requestBuilder.location(filters.getLocations()[i]).build());
					LinkedList<StatisticsRecord> someStatistics = new LinkedList<>();
					
					// Filtro prima di effettuare le statistiche
					someRecords = applyOffAPIFilters(someRecords, filters);
					records.addAll(someRecords);
					
					if ((someRecords.size() != 0) && (filters.isStatisticsIncluded())) {
						someStatistics.add(StatisticsRecord.getStatisticsFromCollection(filters.getLocations()[i], someRecords));
						statistics.addAll(someStatistics);				
					}
					
				}
				catch (Unable2ReachAPI u2rAPI) {
					responseEntity = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
				}
				catch (Exception e) {
					e.printStackTrace();
					responseEntity = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT); // Per ridere
				}
			}
	
			if (!filters.isStatisticsIncluded())
				statistics = null;
				
			responseEntity = new ResponseEntity<>(new SearchResult(records, statistics), HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	/**
	 * @param	collection	Lista di risultati da filtrare
	 * @param	filters	Oggetto contenente i parametri di filtraggio.
	 * @return	Lista di risultati filtrata.
	 * 
	 * @implNote	Per migliorare le performance, alcuni filtri vengono calcolati direttamente dall'API di FindWork,
	 * 				creando una prima "scrematura".
	 */
	private LinkedList<JobRecord> applyOffAPIFilters(LinkedList<JobRecord> collection, FilterRequest filters) {
		LinkedList<JobRecord> filteredList = new LinkedList<JobRecord>();
		boolean validItem;
		
		for(int i = 0; i < collection.size(); ++i) {
			JobRecord item = collection.get(i);
			validItem = true;
						
			if (item.getEmployment() == null)
				validItem = false;
			
			if (filters.getKeywords() != null)
				filters.setMinKeywords(filters.getKeywords().length);
			if (filters.getMinKeywords() == null)
				filters.setMinKeywords(0);
			if (filters.getMaxKeywords() == null)
				filters.setMaxKeywords(Integer.MAX_VALUE);
			
			if ((item.getKeywords().size() < filters.getMinKeywords()) || (item.getKeywords().size() > filters.getMaxKeywords()))
				validItem = false;
			
			if (validItem)
				filteredList.add(item);
		}
		
		return filteredList;
	}
}
