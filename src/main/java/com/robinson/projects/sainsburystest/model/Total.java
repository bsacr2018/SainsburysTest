package com.robinson.projects.sainsburystest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinson.projects.sainsburystest.jsonhandler.CustomDoubleSerializer;

public class Total {
	public static final int VAT_RATE = 20;
	final static BigDecimal VERY_SMALL_AMOUNT = BigDecimal.valueOf(1e-7);
	final static BigDecimal ZERO = BigDecimal.valueOf(0);
	
	// Ensure that a JSON serializer will handle these double values correctly, and
	// display to 2 decimal places
	@JsonSerialize(using = CustomDoubleSerializer.class)
	private Double gross;
	@JsonSerialize(using = CustomDoubleSerializer.class)
	private Double vat;

	public Total() {
		setGross(0.0);
		setVat(0.0);
	}

	// addToTotal() determines that both gross and vat to be updated
	// The new gross amount is simply the sum of the existing gross and the new value
	// The new vat amount is determined using BigDecimal, equivalent to:
	// vat = gross * (1.0 - 1.0 / (1.0 + VAT_RATE/100.0))
	public void addToTotal(double value) {		
		setGross(gross + value);
		
		BigDecimal grossBD = new BigDecimal(gross);
		BigDecimal inversion = new BigDecimal(1.0).divide(
				new BigDecimal(1.0 + VAT_RATE/100.0), 12, RoundingMode.HALF_UP);		
		BigDecimal subtraction = new BigDecimal(1.0).subtract(inversion);
		BigDecimal vatBD = grossBD.multiply(subtraction);
		
		setVat(vatBD.doubleValue());
	}

	public double getGross() {
		return gross;
	}
	
	public void setGross(double gross) {
		BigDecimal bd = new BigDecimal(gross).setScale(2, RoundingMode.FLOOR);
		this.gross = bd.doubleValue();
	}
	
	public double getVat() {
		return vat;
	}
	
	
	public void setVat(double vat) {
		BigDecimal bd = new BigDecimal(vat).setScale(2, RoundingMode.FLOOR);
		this.vat = bd.doubleValue();
	}

//	// The regular case, in the else condition, is simply to ensure that the vat is recorded 
//	// as the whole number of pence, effectively truncating the value.
//	// The only case where rounding up is legitimate is where the difference between
//	// the potential rounded-up value and the original value is very small and where
//	// the next digit is 5 or above.
//	public void setVat(double vat) {
////		BigDecimal roundedUp = new BigDecimal(vat).setScale(7, RoundingMode.HALF_UP);
////		BigDecimal difference = roundedUp.subtract(BigDecimal.valueOf(vat));
////
////		if (vat > 0.0 && difference.compareTo(ZERO) == 1 && difference.compareTo(VERY_SMALL_AMOUNT) == -1) { 
////			this.vat = roundedUp.doubleValue();
////		} else {
//			BigDecimal bd = new BigDecimal(vat).setScale(2, RoundingMode.FLOOR);
//			this.vat = bd.doubleValue();
////		}
//	}
//	
}
