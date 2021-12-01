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
	
	public APIController() {
		this.apiService = new FindWorkService();
	}

	@GetMapping("suggestions") // TODO da rendere dinamico
	public @ResponseBody String suggestLocations() {
		return "[\"LONDON\", \"BERLIN\", \"PLANO\"]";
	}
	
	@GetMapping("search")
	public @ResponseBody ResponseEntity<SearchResult> search(@RequestBody FilterRequest filters) {
		ResponseEntity<SearchResult> responseEntity = null;
		
		LinkedList<JobRecord> records = new LinkedList<>();
		LinkedList<StatisticsRecord> statistics = new LinkedList<>();
		
		// Sfrutto i filtri di findwork.dev
		FindWorkService.RequestBuilder requestBuilder = new FindWorkService.RequestBuilder();
		requestBuilder
			.employment(filters.getEmployment())
			.keywords(filters.getKeywords())
			.remote(filters.isRemote());
		
		/*if (filters.getKeywords() != null)
			requestBuilder.keywords(filters.getKeywords());
		else
			requestBuilder.keywords(new String[0]);
		
		if (filters.isRemote() != null)
			requestBuilder.remote(filters.isRemote());*/
			
		// Temporaneo
		for(int i = 0; i < filters.getLocations().length; ++i) {
			try {
				LinkedList<JobRecord> someRecords = this.apiService.getFullAPIResponse(requestBuilder.location(filters.getLocations()[i]).build());
				LinkedList<StatisticsRecord> someStatistics = new LinkedList<>();
				
				//someRecords = removeInvalid(someRecords);
				// Filtro prima di effettuare le statistiche
				someRecords = applyOffAPIFilters(someRecords, filters);
				records.addAll(someRecords);
				
				if ((someRecords.size() != 0) && (filters.isStatisticsIncluded())) {
					someStatistics.add(StatisticsRecord.getStatisticsFromCollection(filters.getLocations()[i], someRecords));
					statistics.addAll(someStatistics);				
				}	
				// TODO eventuale filtro statistiche
				
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
		
		return responseEntity;
	}
	
	/*private LinkedList<JobRecord> removeInvalid(LinkedList<JobRecord> collection) {
		LinkedList<JobRecord> fixed = new LinkedList<JobRecord>();
		
		for(int i = 0; i < collection.size(); ++i) {
			if (collection.get(i).getEmployment() != null)
				fixed.add(collection.get(i));
		}
		
		return fixed;
	}*/
	
	private LinkedList<JobRecord> applyOffAPIFilters(LinkedList<JobRecord> collection, FilterRequest filters) {
		LinkedList<JobRecord> filteredList = new LinkedList<JobRecord>();
		boolean validItem;

		System.err.println(filters.getMinKeywords());
		System.err.println(filters.getMaxKeywords());
		System.err.println("==============================");
		
		
		for(int i = 0; i < collection.size(); ++i) {
			JobRecord item = collection.get(i);
			validItem = true;
			
			System.err.println(item.getKeywords().size());
			
			if (item.getEmployment() == null)
				validItem = false;
			
			if ((item.getKeywords().size() < filters.getMinKeywords()) || (item.getKeywords().size() > filters.getMaxKeywords()))
				validItem = false;
			
			/*if ((filters.getRole() == null) || (!item.getRole().toLowerCase().contains(filters.getRole())))
				validItem = false;
			/*if ((item.getEmployment() != null) &&
				(item.getKeywords().size() >= filters.getMinKeywords()) &&
				(item.getKeywords().size() <= filters.getMaxKeywords()) &&
				(item.getRole().toLowerCase().contains(filters.getRole().toLowerCase())))*/
			
			if (validItem)
				filteredList.add(item);
		}
		
		return filteredList;
	}
}
