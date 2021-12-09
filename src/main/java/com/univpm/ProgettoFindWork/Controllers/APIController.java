package com.univpm.ProgettoFindWork.Controllers;

import java.util.LinkedList;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.univpm.ProgettoFindWork.Models.FilterRequest;
import com.univpm.ProgettoFindWork.Models.JobRecord;
import com.univpm.ProgettoFindWork.Models.SearchResult;
import com.univpm.ProgettoFindWork.Models.StatisticsRecord;
import com.univpm.ProgettoFindWork.Models.Exceptions.Unable2ReachAPIException;
import com.univpm.ProgettoFindWork.Services.FindWorkService;



@RestController
@RequestMapping("api")
@CrossOrigin
public class APIController {

	private FindWorkService apiService;
	
	public APIController() {
		this.apiService = new FindWorkService();
	}

	/**
	 * Route che suggerisce dei luoghi dove cercare dei lavori tramite l'API di FindWork.
	 * @return	Lista di luoghi
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
	@PostMapping("search")
	public @ResponseBody ResponseEntity<SearchResult> search(@RequestBody FilterRequest filters) {
		ResponseEntity<SearchResult> responseEntity = null;
		
		// Validiamo i luoghi in cui cercare
		if ((filters.getLocations() == null) || (filters.getLocations().length == 0))
			responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else {
			// Lista dei luoghi validi
			LinkedList<String> validLocations = new LinkedList<>();
			// Per ogni luogo controllo se è valido.
			for(int i = 0; i < filters.getLocations().length; ++i)
				if ((filters.getLocations()[i] != null) && (!filters.getLocations()[i].isBlank()))
					validLocations.add(filters.getLocations()[i].toLowerCase()); // Se valido lo aggiungo alla lista
			
			// Sovrascrivo la lista richiesta con la lista valida
			String[] fixed = new String[validLocations.size()];
			for (int i = 0; i < validLocations.size(); ++i)
				fixed[i] = validLocations.get(i);
			filters.setLocations(fixed);
			
		}
		
		
		// Se assenti viene restituito un errore
		if (filters.getLocations().length == 0)
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
				
			
			try {
				// Per ogni luogo andiamo ad effettuare una richiesta
				for(int i = 0; i < filters.getLocations().length; ++i) {
					LinkedList<JobRecord> someRecords = this.apiService.getFullAPIResponse(requestBuilder.location(filters.getLocations()[i]).build());
					LinkedList<StatisticsRecord> someStatistics = new LinkedList<>();
					
					// Filtriamo prima di effettuare le statistiche
					someRecords = applyOffAPIFilters(someRecords, filters);
					records.addAll(someRecords);
					
					// Se non richiedo le statistiche oppure non ottengo risultati dall'API non calcolo le statistiche per il luogo
					if ((someRecords.size() != 0) && (filters.isStatisticsIncluded())) {
						someStatistics.add(StatisticsRecord.getStatisticsFromCollection(filters.getLocations()[i], someRecords));
						statistics.addAll(someStatistics);				
					}
				}
		
				// Se non vogliamo avere le statistiche, le settiamo a null
				if (!filters.isStatisticsIncluded())
					statistics = null;
				
				// Restituiamo i risultati della ricerca, che verrano convertiti in JSON da SpringBoot
				responseEntity = new ResponseEntity<>(new SearchResult(records, statistics), HttpStatus.OK);
			}
			catch (Unable2ReachAPIException u2rAPI) {
				// Se non riesciamo a contattare FindWork, restituiremo uno status SERVICE UNAVAILEBLE
				responseEntity = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
			}
			catch (Exception e) {
				e.printStackTrace();
				// Se dovessero esserci eccezioni inattese, restituiremo uno status I AM A TEAPOT
				responseEntity = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT); // Per ridere
			}
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
		
		// Per ogni record
		for(int i = 0; i < collection.size(); ++i) {
			// Memorizzo il record corrente
			JobRecord item = collection.get(i);
			// Assumo che sia valido
			validItem = true;
			
			// Se l'employment è nullo il record non è valido
			if (item.getEmployment() == null)
				validItem = false;
			
			// Se vi sono delle keywords da cercare, il numero minimo di keywords dev'essere il numero di quelle richieste
			if (filters.getKeywords() != null)
				filters.setMinKeywords(filters.getKeywords().length);
			// Se non è specificato un numero minimo di keywords, si assume che sia 0
			if (filters.getMinKeywords() == null)
				filters.setMinKeywords(0);
			// Se non è specificato un numero massimo di keywords, si assume che sia il massimo rappresentabile da un intero
			if (filters.getMaxKeywords() == null)
				filters.setMaxKeywords(Integer.MAX_VALUE);
			
			// Se le keywords non rientrano nel range richiesto, il record non è valido
			if ((item.getKeywords().size() < filters.getMinKeywords()) || (item.getKeywords().size() > filters.getMaxKeywords()))
				validItem = false;
			
			// Se il ruolo non è contenuto nel record, esso non è valido
			if ((filters.getRole() != null) && (!item.getRole().toLowerCase().contains(filters.getRole().toLowerCase())))
				validItem = false;
			
			// Se il record è valido, lo aggiungiamo alla lista filtrata
			if (validItem)
				filteredList.add(item);
		}
		
		return filteredList;
	}
}
