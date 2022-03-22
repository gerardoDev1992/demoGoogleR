package com.example.demo.entities;


/** Represents a Suggestion.
 * @author Gerardo Leyva 
 * @version 1.0
*/

public class Suggestion {
	
	/** attribute that represent a name of a city*/
	private String name;
	/** attribute that represent a point gps */
	private Double latitude;
	/** attribute that represent a point gps */
	private Double longitude;
	/** attribute that represent a distance or score between a begin point and end point*/
	private Double score;
	
	
	
	
	/**
	* Returns a name of a city. 
	* @return  name   the value of a city
	*/
	public String getName() {
		return name;
	}
	 /**
     * This method is for begin the name of the city
     * with this method we can inicialats a value or change the value
     * @param name  String value represents name of a city
     */ 
	public void setName(String name) {
		this.name = name;
	}
	/**
	* Returns a double value that represent a point gps example 79.1023
	* @return   latitude  the value of a point
	*/
	public Double getLatitude() {
		return latitude;
	}
	
	 /**
     * This method is for begin the value of latitude
     * with this method we can inicialate a value or change the value
     * @param latitude  Double value represent a point  
     */	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	* Returns a double value that represent a point gps example -81.1023
	* @return   longitude   the value of a point
	*/
	public Double getLongitude() {
		return longitude;
	}
	 /**
     * This method is for begin the value of longitude
     * with this method we can inicialate a value or change the value
     * @param longitude  Double value represent a point  
     */	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	* Returns a double value that represent score between latitude and longitude origin to final latitude and longitude 
	* @return   score   the score valur 0 to 1
	*/
	public Double getScore() {
		return score;
	}
	/**
     * This method is for begin the value of score
     * with this method we can inicialate a value or change the value
     * @param score  Double value represent a point  
     */	
	public void setScore(Double score) {
		this.score = score;
	}
	

	

}
