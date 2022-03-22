package com.example.demo.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import org.springframework.web.bind.annotation.GetMapping;



import com.example.demo.entities.Suggestion;
/** Represents a Suggestion Service.
 * @author Gerardo Leyva 
 * @version 1.0
*/

@Service
public class SuggestionService {
	


    @Value("gs://spring-buckettsv-gerardodev1992/citiescanadausa.tsv")
    private Resource gcsFile;
    /*
    @GetMapping
    public String readGcsFile() throws IOException {
        return StreamUtils.copyToString(
            gcsFile.getInputStream(),
            Charset.defaultCharset());
    }*/




	static BufferedReader reader = null;
	 /**
     * This method convert a .tsv file to stream to manipulate the info 
     * with this method get all necessary info
     * @return  ArrayList posible  Suggestion
     */	
	public ArrayList<Suggestion> getCities() throws IOException {		
		ArrayList<Suggestion> cities=new ArrayList<Suggestion>();
		try {
				
            String g=StreamUtils.copyToString(
			        gcsFile.getInputStream(),
			        Charset.defaultCharset());
			
            

		     
              

		      
		      int i=0;
              String line="";
              
              String[] tsv = g.split("\n");
	        
              for (String lenguaje: tsv) {
               
                 
                  if(i>0) {
                            		       
                    String[] tokens = lenguaje.split("\t");
                    Suggestion city=new Suggestion();
                    city.setName(tokens[1]+", "+tokens[10]+", "+tokens[8]);		        
                    city.setLatitude(Double.parseDouble(tokens[4]));
                    city.setLongitude(Double.parseDouble(tokens[5]));		        
                    cities.add(city);
                    }
                    i++;
                  
              }
  
/*
		      while ((line = g.readLine())!= null) {
		        
		        if(i>0) {
		        String[] tokens = line.split("\t");		        		       
		        Suggestion city=new Suggestion();
		        city.setName(tokens[1]+", "+tokens[10]+", "+tokens[8]);		        
		        city.setLatitude(Double.parseDouble(tokens[4]));
		        city.setLongitude(Double.parseDouble(tokens[5]));		        
		        cities.add(city);
		        }
		        i++;
		      }
*/		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return cities;
	}
	 /**
     * This method is for calculate the score between two points     
     * @param oriLatitude  Double value represent a point
     * @param oriLongitude  Double value represent a point  
     * @param des Suggestion this object contains a posible Suggestion 
     * @return score Double the calculated value for the Suggestion
     */	
	public Double score( double oriLatitude,double oriLongitude,Suggestion des) {
		DecimalFormat df = new DecimalFormat("#.#");
		double latitud = Math.abs(des.getLatitude() - oriLatitude);
		double longi= Math.abs(des.getLongitude() - oriLongitude);
		double score = 10 - (latitud + longi) / 2;
		
		score=score/10;
	    if(score>0 && score<=1) {
	    	score = Double.valueOf(df.format(score));		    	
	    }else {
	    	score=0;
	    }		    		 
	    return score;
	}

	
	 /**
     * This method get suggestions and sort based on his score
     * @param name String name of the posible Suggestion     
     * @param lat  Double value represent a point
     * @param lon  Double value represent a point   
     * @return ass  String JSONOBject of suggestions
     */	
	public String scoredSuggestedMatches(String name,Double lat,Double lon) throws IOException {			
		
		List<Suggestion> filtList = getCities().stream().
		         filter(value -> value.getName().toUpperCase().
		         contains(name.toUpperCase())).collect(Collectors.toList());
		
		filtList.forEach(x->x.setScore(score(lat,lon ,x)));
		
		
		List<Suggestion> sortedList = filtList.stream()
		        .sorted(Comparator.comparingDouble(Suggestion::getScore).reversed())
		        .collect(Collectors.toList());
		
		JSONArray arr=new JSONArray();
		JSONObject as=null;
		for(Suggestion suggestion : sortedList) {
			as=new JSONObject();
								
			as.put("latitude", suggestion.getLatitude());
			as.put("longitud", suggestion.getLongitude());
			as.put("score", suggestion.getScore());
			as.put("name", suggestion.getName());	
			
			arr.put(as);
			
			
		}
	
		
		JSONObject ass=new JSONObject();
		
		ass.put("suggestions", arr);
		//
		
		
		
		return ass.toString();
		
		
	}

}
