package com.robinson.projects.sainsburystest.jsonhandler;

public class Total {
	public static final int VAT_RATE = 20;
	double gross;
	double vat;

	public Total() {
		gross = 0.0;
		vat = 0.0;
	}

	void addToTotal(double value) {
		gross += value;
		double vatPrecise = gross * (1.0 - 1.0 / (1.0 + VAT_RATE/100.0));
		vat = Double.parseDouble(String.format("%.2f", vatPrecise));
	}

	public double getGross() {
		return gross;
	}
	
	public void setGross(double gross) {
		this.gross = gross;
	}
	
	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}
}
