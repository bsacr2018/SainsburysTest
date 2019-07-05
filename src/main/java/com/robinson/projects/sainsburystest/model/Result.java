package com.robinson.projects.sainsburystest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.robinson.projects.sainsburystest.jsonhandler.CustomDoubleSerializer;

public class Result {
	static ObjectMapper mapper = new ObjectMapper();
	static ObjectWriter regularJSONWriter = mapper.writer();
	static ObjectWriter prettyJSONWriter = mapper.writerWithDefaultPrettyPrinter();
	
	private String title;
	@JsonInclude(Include.NON_NULL)	
	private Integer kcal_per_100g = null;
	@JsonSerialize(using = CustomDoubleSerializer.class)
	private Double unit_price;	
	private String description;

	public Result(String title, Optional<Integer> kcal_per_100g, double unit_price, String description) {
		this.title = title;
		if (kcal_per_100g.isPresent()) {
			this.setKcal_per_100g(kcal_per_100g.get());
		}
		setUnit_price(unit_price);
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

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		BigDecimal bd = BigDecimal.valueOf(unit_price).setScale(2, RoundingMode.FLOOR);
		this.unit_price = bd.doubleValue();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
		
