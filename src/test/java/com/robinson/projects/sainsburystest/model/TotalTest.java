package com.robinson.projects.sainsburystest.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.robinson.projects.sainsburystest.model.Total;

public class TotalTest {

	private Total total;
	
	@Test
	void testThat_ZeroPoundsTotal_resultsIn_0PouundsGross_and_0VAT() {
		total = new Total();
		assertEquals(0.0, total.getGross());
		assertEquals(0.0, total.getVat());		
	}

	@Test
	void testThat_120PoundsTotal_resultsIn_120Gross_and_20VAT() {
		total = new Total();
		total.addToTotal(120.0);
		assertEquals(120.0, total.getGross());
		assertEquals(20.0, total.getVat());
	}
	
	@Test
	void testThat_ItemsThatSumTo120Pounds_resultsIn_120Gross_20Vat() {
		total = new Total();
		total.addToTotal(60.0);
		total.addToTotal(60.0);
		assertEquals(120.0, total.getGross());
		assertEquals(20.0, total.getVat());		
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
