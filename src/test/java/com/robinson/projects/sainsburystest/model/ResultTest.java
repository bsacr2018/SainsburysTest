package com.robinson.projects.sainsburystest.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.robinson.projects.sainsburystest.model.Details;
import com.robinson.projects.sainsburystest.model.DisplayableProducts;
import com.robinson.projects.sainsburystest.model.Product;
import com.robinson.projects.sainsburystest.model.Result;

public class ResultTest {
	static ObjectMapper mapper = new ObjectMapper();
	static ObjectWriter regularJSONWriter = mapper.writer();

	@Test
	public void TestBlueberriesWithNoCalorificValue() {		
		List<Product> products = new ArrayList<Product>();
		Product blueberries = new Product("http://", "blueberries");
		Details details = new Details("blueberries", 1.75, "By Sainsbury's blueberries", Optional.empty());
		blueberries.setDetails(details);
		products.add(blueberries);

		DisplayableProducts dps = new DisplayableProducts();
		dps.add(blueberries);

		Result result = dps.getResults().get(0);

		String regularJSON = null;
		try {
			regularJSON = regularJSONWriter.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			assertFalse(true);
		}

		assertEquals("{\"title\":\"blueberries\",\"unit_price\":1.75,\"description\":\"By Sainsbury's blueberries\"}", 
				regularJSON);
	}

	@Test
	public void TestBlueberriesWithACalorificValue() {		
		List<Product> products = new ArrayList<Product>();
		Product blueberries = new Product("http://", "blueberries");
		Details details = new Details("blueberries", 1.85, "By Sainsbury's blueberries", Optional.of(45));
		blueberries.setDetails(details);
		products.add(blueberries);

		DisplayableProducts dps = new DisplayableProducts();
		dps.add(blueberries);
		
		Result result = dps.getResults().get(0);

		String regularJSON = null;
		try {
			regularJSON = regularJSONWriter.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
		assertEquals("{\"title\":\"blueberries\",\"kcal_per_100g\":45,\"unit_price\":1.85,\"description\":\"By Sainsbury's blueberries\"}", 
				regularJSON);
	}

}
