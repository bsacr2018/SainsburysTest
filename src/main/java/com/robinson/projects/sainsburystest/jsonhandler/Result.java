package com.robinson.projects.sainsburystest.jsonhandler;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Result {

	static ObjectMapper mapper = new ObjectMapper();
	static ObjectWriter regularJSONWriter = mapper.writer();
	static ObjectWriter prettyJSONWriter = mapper.writerWithDefaultPrettyPrinter();
	
	private String title;
	private Integer kcal_per_100g = null; 
	private double unit_price;
	private String description;

	public Result(String title, Optional<Integer> kcal_per_100g, double unit_price, String description) {
		this.title = title;
		if (kcal_per_100g.isPresent()) {
			this.setKcal_per_100g(kcal_per_100g.get());
		}
		this.unit_price = unit_price;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getKcal_per_100g() {
			return kcal_per_100g;
	}

	public void setKcal_per_100g(Integer kcal_per_100g) {
		this.kcal_per_100g = kcal_per_100g;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toJSON() {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.valueToTree(this);
		ObjectNode node = (ObjectNode) jsonNode;

		JsonNode n = node.get("kcal_per_100g");
		if (n.getNodeType().name().equals("NULL")) {
			JsonNode removedNode = ((ObjectNode) jsonNode).remove("kcal_per_100g");
		}
		
		try {
			return regularJSONWriter.writeValueAsString(jsonNode);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return "error";
		
	}
}
		
