package com.robinson.projects.sainsburystest;

import java.util.Optional;

public class Product {
	private String absoluteWebAddress;
	private String name;
	private double unitPrice;
	private String description;
	private Optional<Integer> kcal_per_100g = Optional.empty();
		
	public Product(String absoluteWebAddress, String name, double unitPrice) {
		this.absoluteWebAddress = absoluteWebAddress;
		this.name = name;
		this.unitPrice = unitPrice;
	}
	
	public void additionalInfo(String description, Optional<Integer> kcal_per_100g) {
		this.description = description;
		this.kcal_per_100g = kcal_per_100g;
	}
	
	public String getAbsoluteWebAddress() {
		return absoluteWebAddress;
	}

	public void setAbsoluteWebAddress(String absoluteWebAddress) {
		this.absoluteWebAddress = absoluteWebAddress;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
		
	public Optional<Integer> getKcal_per_100g() {
		return kcal_per_100g;
	}

	public void setKcal_per_100g(Optional<Integer> kcal_per_100g) {
		this.kcal_per_100g = kcal_per_100g;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
