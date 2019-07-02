package com.robinson.projects.sainsburystest.jsonhandler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TotalTest {
	
	private Total total;

	@Test
	void testThat120Pounds_resultsIn_120Gross_and_20VAT() {
		total = new Total();
		total.addToTotal(120);
		assertEquals(120.0, total.getGross());
		assertEquals(20, total.getVat());		
	}

	
	@Test
	void testThat_ItemsThatSumTo120Pounds_resultsIn_120Gross_20Vat() {
		total = new Total();
		total.addToTotal(60);
		total.addToTotal(60);
		assertEquals(120.0, total.getGross());
		assertEquals(20, total.getVat());		
	}
	
	
	@Test
	void testThat_ItemsThatSumTo5Pounds_resultsIn_5PouundsGross_83PenceVat() {
		total = new Total();
		total.addToTotal(2.0);
		total.addToTotal(2.0);
		total.addToTotal(1.0);
		assertEquals(5.0, total.getGross());
		assertEquals(0.83, total.getVat());		
	}
	
	
}
