package com.robinson.projects.sainsburystest.model;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.robinson.projects.sainsburystest.model.Details;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Product {
	private String webAddress;
	private String presumedName;
	private Details details;

	public Product(String webAddress, String presumedName) {
		this.webAddress = webAddress;
		this.presumedName = presumedName;
	}

	public void setAdditionalInfo(Details details) {
		this.details = details;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	private boolean titlesMatch = false;

	public boolean isTitlesMatch() {
		return titlesMatch;
	}

	void determineIfTitlesMatch(Document productDoc) {
		Elements metaTags = productDoc.getElementsByTag("meta");

		String title = "";
		for (Element metaTag : metaTags) {
			if (metaTag.attr("property").equals("og:title")) {
				title = metaTag.attr("content");
				break;
			}
		}
		this.titlesMatch = title.equals(presumedName) ? true : false;
	}

	double determineUnitPrice(Document productDoc) {
		Element price = productDoc.selectFirst("p[class=pricePerUnit]");
		return Double.parseDouble(price.text().substring(1,price.text().length()-5));
	}


	final static Pattern usualPattern = Pattern.compile("(\\d+)kcal");
	final static Pattern specialPattern = Pattern.compile("kcal\\s(\\d+)");

	// In the entries where a calorific value is specified, most of them have the number
	// listed prior to kcal unit, but in one case it is listed first.
	int determineCalories(Document productDoc) {		
		int caloriesPer100g = -1;
		Element nutritionTable = productDoc.selectFirst("table[class=nutritionTable]");
		Elements elements = null;
		if (nutritionTable != null) {
			elements = nutritionTable.children();
			Element secondElement = elements.get(1);
			Elements children = secondElement.children();
			Element child = children.get(1);
			String calorieText = child.text();
			if (calorieText.substring(0,6).equals("Energy")) {
				Matcher matcher = specialPattern.matcher(calorieText);
				if (matcher.find()) {
					caloriesPer100g = Integer.parseInt(matcher.group(1));
				}
			} else {
				Matcher matcher = usualPattern.matcher(calorieText);
				if (matcher.find()) {
					caloriesPer100g = Integer.parseInt(matcher.group(1));
				}
			}
		}

		return caloriesPer100g;
	}

	public String determineDescription(Document productDoc) {
		Element information = productDoc.getElementById("information");
		Element item = information.getAllElements().get(0);

		Element firstItem  = null;
		Element secondItem  = null;
		Elements elements = item.getElementsByClass("productDataItemHeader");

		if (elements.isEmpty()) {
			elements = item.getElementsByClass("itemTypeGroupContainer");
			if (!elements.isEmpty()) {
				firstItem = elements.first();
			}
		}

		Element firstElement = elements.first();
		String text = firstElement.text();

		if (firstItem != null) {						
			text = firstItem.text();
			Elements memos = firstItem.getElementsByClass("memo");
			if (!memos.isEmpty()) {
				secondItem = memos.first();
			} else {
				memos = firstItem.getElementsByClass("itemTypeGroup");
				if (!memos.isEmpty()) {
					secondItem = memos.first();
				} 
			}
		} 

		String description = text.equals("Description") ? 
				firstElement.nextElementSiblings().get(0).text() :
					secondItem.text();

		return description;
	}


	// This method handles the four possible values that we are concerned about for each of our products
	public void determineDetails() {
		try {
			Document productDoc = Jsoup.connect(webAddress).get();			
			determineIfTitlesMatch(productDoc);	

			Double unitPrice = determineUnitPrice(productDoc);

			int caloriesPer100g = determineCalories(productDoc);
			Optional<Integer> kcalPer100g = caloriesPer100g > 0 ? Optional.of(caloriesPer100g)
					: Optional.empty();

			String description = determineDescription(productDoc);

			this.details = new Details(presumedName, unitPrice, description, kcalPer100g);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public String getWebAddress() {
		return webAddress;
	}

	public String getPresumedName() {
		return presumedName;
	}

}
