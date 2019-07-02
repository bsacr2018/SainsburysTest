package com.robinson.projects.sainsburystest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.robinson.projects.sainsburystest.jsonhandler.*;

public class ScraperTask {

	public static void main(String[] args) {
		List<Product> products = new ArrayList<Product>();
		
		//Establish Products	
		Product blueberries = new Product("http://", "blueberries", 1.75);		
		blueberries.setDescription("By Sainsburys");
		blueberries.setKcal_per_100g(Optional.of(40));		
		products.add(blueberries);

		DisplayableProduct dps = new DisplayableProduct();
		dps.add(blueberries);
		
		// Display indivdual results
		List<Result> rs = dps.getResults();
		for (Result r : rs) {
			System.out.println(r.toJSON());
		}
	}

}
