package com.robinson.projects.sainsburystest.jsonhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.robinson.projects.sainsburystest.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class DisplayableProduct {
	static ObjectMapper mapper = new ObjectMapper();
	static ObjectWriter regularJSONWriter = mapper.writer();
	static ObjectWriter prettyJSONWriter = mapper.writerWithDefaultPrettyPrinter();

	List<Result> results;
	Total total;

	public DisplayableProduct() {
		results = new ArrayList<Result>();
		total = new Total();
	}

	public void add(Product scrapedProduct) {
		results.add(new Result(scrapedProduct.getName(), 
				scrapedProduct.getKcal_per_100g(), 
				scrapedProduct.getUnitPrice(), 
				scrapedProduct.getDescription())
				);
		total.addToTotal(scrapedProduct.getUnitPrice());
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

	public String toString() {
		return generateJSON(regularJSONWriter);
	}

	public String displayJSON() {
		return generateJSON(prettyJSONWriter);
	}

	public String generateJSON(ObjectWriter objectWriter) {
        return "";
	}

}
