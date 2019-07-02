package com.robinson.projects.sainsburystest.jsonhandler;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.robinson.projects.sainsburystest.Product;

public class ResultTest {
	
	@Test
	public void TestBlueberriesWithNoCalorificValue() {		
		List<Product> products = new ArrayList<Product>();
		Product blueberries = new Product("http://", "blueberries", 1.75);		
		products.add(blueberries);

		DisplayableProduct dps = new DisplayableProduct();
		dps.add(blueberries);
		
		List<Result> rs = dps.getResults();
		Result r = rs.get(0);
        String regularJSON = r.toJSON();
		
		assertEquals("{\"title\":\"blueberries\",\"unit_price\":1.75,\"description\":null}", regularJSON);
		
	}
	
	@Test
	public void TestBlueberriesWithACalorificValue() {
		
		List<Product> products = new ArrayList<Product>();
		Product blueberries = new Product("http://", "blueberries", 1.85);		
		blueberries.setKcal_per_100g(Optional.of(45));	
		blueberries.setDescription("By Sainsbury's blueberries");
		products.add(blueberries);


		DisplayableProduct dps = new DisplayableProduct();
		dps.add(blueberries);
		
		List<Result> rs = dps.getResults();
		Result r = rs.get(0);
        String regularJSON = r.toJSON();
        
        assertEquals("{\"title\":\"blueberries\",\"kcal_per_100g\":45,\"unit_price\":1.85,\"description\":\"By Sainsbury's blueberries\"}", regularJSON);
		
	}
}
