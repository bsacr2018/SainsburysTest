package com.robinson.projects.sainsburystest.model;

import java.util.ArrayList;
import java.util.List;

public class DisplayableProducts {
	private List<Result> results;
	private Total total;

	public DisplayableProducts() {
		results = new ArrayList<Result>();
		total = new Total();
	}

	public void add(Product scrapedProduct) {
		Details details = scrapedProduct.getDetails();
		results.add(new Result(details.getProductTitle(), 
				details.getKcalPer100g(), 
				details.getUnitPrice(), 
				details.getDescription())
				);
		total.addToTotal(details.getUnitPrice());
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> products) {
		this.results = products;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

}
