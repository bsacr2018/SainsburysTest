package com.robinson.projects.sainsburystest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Formatter;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinson.projects.sainsburystest.model.Details;

public class DetailsTest {	
	ObjectMapper mapper;

	@BeforeEach 
	public void setup() {
		mapper = new ObjectMapper();
	}

	static double truncate(double value, int places) {
		return new BigDecimal(value)
				.setScale(places, RoundingMode.DOWN)
				.doubleValue();
	}

	// This test goes through all values from [1.0, 1.999] in tenths of a penny
	// to ensure that when JSON is generated, the 2 decimal places are correct
	@Test
	public void testToProveThatUnitPriceWillBeHandledWith2DecimalPlaces() {
		int count = 0;

		for (int i = 0; i < 1000; i++) {
			double base = 1.0;
			double extra = i / 1000.0;
			Double combinedValue = base + extra;
			
			int i0 = i / 100;
			int i1 = (i % 100) / 10;		
	    	StringBuilder sb = new StringBuilder();
	    	Formatter formatter = new Formatter(sb);
	    	formatter.format("%d%d", i0, i1);
	    	String expectedValueString = new String("1."+sb.toString());
	    	formatter.close();

			String expectedString = "{\"productTitle\":\"strawberries\",\"unitPrice\":" +
					expectedValueString + 
					",\"description\":\"By Sainsbury's strawberries\",\"kcalPer100g\":{\"present\":true}}";

			Details details = new Details("strawberries", combinedValue, 
					"By Sainsbury's strawberries", Optional.of(45));	

			String actualResult = null;
			try {
				actualResult = mapper.writeValueAsString(details);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				assertFalse(true);
			}
			
			count += expectedString.equals(actualResult) ? 1 : 0;

		}

		assertEquals(1000, count);
	}

}
