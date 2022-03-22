package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.SuggestionService;

/** Represents a SuggestionController.
 * @author Gerardo Leyva 
 * @version 1.0
*/

@RestController
public class SuggestionController {
	/** attribute include methods from a service like getcities */
	@Autowired
	private SuggestionService suggestionService;
	  
	
	/**
	* Returns a Sring json like {
  "suggestions": [
    {
      "name": "London, ON, Canada",
      "latitude": "42.98339",
      "longitude": "-81.23304",
      "score": 0.9
    },
    {
      "name": "London, OH, USA",
      "latitude": "39.88645",
      "longitude": "-83.44825",
      "score": 0.5
    },
    {
      "name": "London, KY, USA",
      "latitude": "37.12898",
      "longitude": "-84.08326",
      "score": 0.5
    },
    {
      "name": "Londontowne, MD, USA",
      "latitude": "38.93345",
      "longitude": "-76.54941",
      "score": 0.3
    }
  ]
}. 
     * @param q  queryString The partial (or complete) search term is passed as a querystring parameter
     * @param latitude  Double represent latitude destiny
     * @param longitude  Double represent longitude destiny

	* @return  json return a JSONOBJECT 
	 * @throws IOException 
	 * @throws NumberFormatException 
	*/
	@GetMapping(value = "/suggestions", produces = "application/json")
	public String suggestions(@RequestParam String q,
		      @RequestParam(required = false) Double latitude,
		      @RequestParam(required = false) Double longitude) throws NumberFormatException, IOException {
		String json="";
		json = suggestionService.scoredSuggestedMatches(q,latitude,longitude);
		
		return json;
	}

	@RequestMapping("/_ah/health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("Healthy", HttpStatus.OK);
    }
}
