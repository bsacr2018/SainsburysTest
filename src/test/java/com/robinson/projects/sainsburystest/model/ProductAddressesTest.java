package com.robinson.projects.sainsburystest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.robinson.projects.sainsburystest.htmlhandler.WebInfo;

public class ProductAddressesTest {
	WebInfo webInfo;
	
	@BeforeEach
	public void setup() {
		webInfo = new WebInfo("http://productsPage");
	}

	@Test
	public void testToShowThatPresumedNamesAndWebAddressesCanBeExtractedFromHTMLIfThereIsAMatchForCategoryAndMatchingTerm() {
		String pseudoBaseURL = "http://com.robinson/";
		String relevantCategory = "categoryX/";
		List<String> expectedNames = Arrays.asList(
				new String[] { "nameA", "nameB", "nameC" }
				);
		List<String> expectedWebAddresses = Arrays.asList(
				new String[] { 
						pseudoBaseURL+relevantCategory+"webA", 
						pseudoBaseURL+relevantCategory+"webB", 
						pseudoBaseURL+relevantCategory+"webC"}
				);
		List<String> validatingTerms = Arrays.asList(
				new String[] { "A", "B", "C", "E" }
				);
		
		String productsPageText = 		
		"<!DOCTYPE html>"+
		"<html>"+
		"<head>"+
		"<meta charset=\"ISO-8859-1\">"+
		"<title>CategoryX items</title>"+
		"</head>"+
		"<body>"+

		"<div class=\"productInfo\"><a href=\"./categoryX/webA\">nameA</a></div>"+
		"<div class=\"productInfo\"><a href=\"./categoryX/webB\">nameB</a></div>"+
		"<div class=\"productInfo\"><a href=\"./categoryX/webC\">nameC</a></div>"+
		"<div class=\"productInfo\"><a href=\"./categoryX/webD\">nameD</a></div>"+ // nameD doesn't match a validating Term
		"<div class=\"productInfo\"><a href=\"./categoryY/webE\">nameE</a></div>"+ // nameE is not the right category

		"</body>"+
		"</html>";

		Document document = webInfo.establishDocumentFromHTML(productsPageText, "http://com.robinson/");
		
		List<Product> products = null;
		try {
			products = webInfo.parseDocument(document, validatingTerms, relevantCategory);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		System.out.println("#products="+products.size());

		List<String> actualNames = new ArrayList<String>();
		List<String> actualAddresses = new ArrayList<String>();
		for (Product product : products) {
			actualNames.add(product.getPresumedName());
			actualAddresses.add(product.getWebAddress());
		}

		assertEquals(expectedNames, actualNames);
		assertEquals(expectedWebAddresses, actualAddresses);
	}
}