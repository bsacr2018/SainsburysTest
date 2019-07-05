package com.robinson.projects.sainsburystest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jsoup.nodes.Document;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.robinson.projects.sainsburystest.htmlhandler.WebInfo;
import com.robinson.projects.sainsburystest.model.*;

public class ScraperTask {
	
	public static void main(String[] args) {
		new ScraperTask().run();
	}

	void run() {
		Properties properties = getProperties("config.properties");
		
		String productsPage = properties.getProperty("productsHTMLPage");
		List<String> validatingTerms = Arrays.asList(
				properties.getProperty("validatingTerms").split(","));
		String relevantCategory = properties.getProperty("relevantCategory");
		
	    WebInfo webInfo = new WebInfo(productsPage);	
		Document document = null;
		List<Product> products = null;
		
		System.out.println("Using the following properties-file-derived input data:\n");	
		System.out.println("productsPage="+productsPage);
		System.out.println("validatingTerms="+validatingTerms);
		System.out.println("relevantCategory="+relevantCategory);
		System.out.println();
		// Establish the web addresses and names of the products from the Products page
		// that meet one of the validatingTerms under the relevantCategory on that page
		try {
			document = webInfo.establishDocumentFromURL(productsPage);
			products = webInfo.parseDocument(document, validatingTerms, relevantCategory);
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			System.exit(-1);
		}
		System.out.println("Number of product links accessed is "+products.size());
		System.out.println();
		System.out.println("Determining details from each product webpage:");
		
		// Go through each of the products and establish the key details to be scraped from
		// its web page
		int itemNo = 0;
		int count = 0;
		List<Integer> discrepancies = new ArrayList<Integer>();
		for (Product product : products) {
			System.out.print("["+(++itemNo)+"] ");
			product.determineDetails();
			if (product.isTitlesMatch()) {
				count++;
				discrepancies.add(itemNo);
			}
		}
		System.out.println("\n");
		
		if (count != products.size()) {
			System.out.printf("#titles matching products="+count);
			System.out.printf("==>Discrepancies for items: "+discrepancies+"\n");
		}

		System.out.println("Displaying the JSON of the resulting DisplayableProducts object:");

		DisplayableProducts displayableProducts = new DisplayableProducts();
		for (Product product : products) {
			displayableProducts.add(product);
		}

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter prettyPrinter = mapper.writerWithDefaultPrettyPrinter();

		String prettyFormattedJSON = null;
		try {
			prettyFormattedJSON = prettyPrinter.writeValueAsString(displayableProducts);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println(prettyFormattedJSON);
		System.exit(0);
	}

	Properties getProperties(String propertiesFile) {
		InputStream inputStream = ScraperTask.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
		return properties;
	}





}
