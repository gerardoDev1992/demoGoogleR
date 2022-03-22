package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.entities.Suggestion;

public class SuggestionTest {
	
	Suggestion s=new Suggestion();

	@Test
	void test() {
		s.setName("Canada");
		s.setLatitude(20.30);
		s.setLongitude(-20.20);
		s.setScore(0.90);
		
		assertEquals("Canada",s.getName());
		//assertEquals(20.30,s.getLatitude());
		//assertEquals(-20.20,s.getLongitude());
		//assertEquals(0.90,s.getScore());
		
	}

}
