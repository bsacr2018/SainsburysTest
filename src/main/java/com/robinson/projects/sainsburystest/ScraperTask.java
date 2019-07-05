package com.robinson.projects.sainsburystest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.robinson.projects.sainsburystest.model.*;

public class ScraperTask {
	List<String> validatingTerms;

	static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		new ScraperTask().run();
	}

	void run() {
//		Properties properties = getProperties("config.properties");
//
//		String productsPage = properties.getProperty("productsHTMLPage");
//		List<String> validatingTerms = Arrays.asList(
//				properties.getProperty("validatingTerms").split(","));
//
//		List<Product> products = null;
//		try {
//			products = parseWebsite(productsPage, validatingTerms);
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//			System.exit(-1);
//		}

		Product product1 = new Product("http://page1", "Sainsbury's Strawberries 400g");
		Product product2 = new Product("http://page2", "Sainsbury's Blueberries 200g");
		Product product3 = new Product("http://page3", "Sainsbury's Cherry Punnet 200g");

		Details details1 = new Details("Sainsbury's Strawberries 400g", Double.valueOf(1.75), "by Sainsbury's strawberries",
				Optional.of(33));
		Details details2 = new Details("Sainsbury's Blueberries 200g", Double.valueOf(1.75), "by Sainsbury's blueberries",
				Optional.of(45));
		Details details3 = new Details("Sainsbury's Cherry Punnet 200g", Double.valueOf(1.5), "Cherries", Optional.of(52));

		product1.setDetails(details1);
		product2.setDetails(details2);
		product3.setDetails(details3);

		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);

		System.out.println("****Displayable Products");

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
		System.out.println("||" + properties.getProperty("productsHTMLPage"));
		System.out.println("||" + properties.getProperty("validatingTerms"));
		return properties;
	}

	// The minimum requirement for the product to be deemed valid is that
	// one of the validatingTerms list appears somewhere in the product title
	boolean isValidProduct(String characterSequence, List<String> validatingTerms) {
		for (String term : validatingTerms) {
			if (characterSequence.indexOf(term) > 0) {
				return true;
			}
		}
		return false;
	}

//	List<Product> parseWebsite(String productsPage, List<String> validatingTerms) throws IOException {
//		List<Product> products = new ArrayList<Product>();
//
//		Document productsDocument = Jsoup.connect(productsPage).get();
//		Element content = productsDocument.getElementById("content");
//		Elements links = content.getElementsByTag("a");
//		for (Element link : links) {
//			String relativeWebRef = link.attr("href");
//
//			if (relativeWebRef.indexOf("groceries/berries-cherries-currants") > 0) {
//				String productTitle = link.text();
//				String absoluteWebRef = link.attr("abs:href");
//				if (isValidProduct(absoluteWebRef, validatingTerms)) {
//					Product product = new Product(absoluteWebRef, productTitle);
//					product.determineDetails();
//					products.add(product);
//				}
//			}
//		}
//
//		return products;
//	}

}
