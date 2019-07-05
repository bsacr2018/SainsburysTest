package com.robinson.projects.sainsburystest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinson.projects.sainsburystest.jsonhandler.CustomDoubleSerializer;

public class Details {
//	final static BigDecimal VERY_SMALL_AMOUNT = BigDecimal.valueOf(1e-7);
//	final static BigDecimal ZERO = BigDecimal.valueOf(0);	
	
	private String productTitle;
	@JsonSerialize(using = CustomDoubleSerializer.class)
	private Double unitPrice;
	private String description;
	private Optional<Integer> kcalPer100g;

	public Details(String productTitle, Double unitPrice, String description, Optional<Integer> kcalPer100g) {
		this.productTitle = productTitle;
		this.description = description;
		this.kcalPer100g = kcalPer100g;
		setUnitPrice(unitPrice);
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		BigDecimal roundedUp = new BigDecimal(unitPrice).setScale(6, RoundingMode.HALF_UP);
		this.unitPrice = roundedUp.setScale(2, RoundingMode.FLOOR).doubleValue();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Optional<Integer> getKcalPer100g() {
		return kcalPer100g;
	}

	public void setKcalPer100g(Optional<Integer> kcalPer100g) {
		this.kcalPer100g = kcalPer100g;
	}

	public boolean equals(Object thatObject) {
		if (this == thatObject) {
			return true;
		}
		if (thatObject != null && thatObject instanceof Details) {
			Details that = (Details) thatObject;
			return this.productTitle.equals(that.productTitle) &&
					this.unitPrice == that.unitPrice &&
					this.description.equals(that.description) &&
					this.kcalPer100g.equals(that.kcalPer100g);
		}
		return false;
	}

	public String toString() {
		return "Details{"+productTitle+", "+unitPrice+", "+description+", "+kcalPer100g+"}";
	}

}


