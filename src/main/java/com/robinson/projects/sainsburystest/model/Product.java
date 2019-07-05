package com.robinson.projects.sainsburystest.model;

//import java.io.IOException;
//import java.util.Optional;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Product {
	private String webAddress;
	private String name;
	private Details details;

	public Product(String webAddress, String name) {
		this.webAddress = webAddress;
		this.name = name;
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
	
//	public void determineDetails() {
//		try {
//			System.out.println("additionalInfo");
//			String description = "description-not-initialized";
//			Double unitPrice = 0.0;
//			Document productDoc = Jsoup.connect(webAddress).get();
//		
//			Element price = productDoc.selectFirst("p[class=pricePerUnit]");
//			unitPrice = Double.parseDouble(price.text().substring(1,price.text().length()-5));
//			System.out.println("pricePerUnit="+unitPrice);
//			
//			int caloriesPer100g = -1;
//
//			Element information = productDoc.getElementById("information");
//			//				Elements body = productDoc.getElementsByClass("section");
//			//				Element information = productDoc.select("id#information").first();
//			Element item = information.getAllElements().get(0);
//			//				System.out.println(information.text());
//
//			//System.out.println("#elements="+elements.size());
//			//for (Element item : elements) {
//
//			//System.out.println(""+(count++)+". "+presumedProductTitle);//+item);
//			System.out.println(webAddress.substring(75));
//			System.out.println("name="+name);
//			Element firstItem  = null;
//			Element secondItem  = null;
//
//			Elements zs = item.getElementsByClass("productDataItemHeader");
//			if (zs.isEmpty()) {
//				System.out.println("==>AAA");
//				zs = item.getElementsByClass("itemTypeGroupContainer");
//				if (zs.isEmpty()) {
//					System.out.println("*************Different type of class matching required***");
//				} else {
//					//							System.out.println("////");
//					for (Element z : zs) {
//						//								if (secondItem == null && firstItem != null) {
//						//									secondItem = z;
//						//								}
//						if (firstItem == null) {
//							firstItem = z;
//						}
//
//						//								System.out.println("z->"+z);
//					}
//					//							System.out.println("]]]]");
//				}
//			}
//			//for (Element z : zs ) {
//			Element z = zs.first();
//			//						System.out.println("z -> "+z);
//			String t = z.text();
//			Element zz = null;
//
//			if (firstItem != null) {						
//				t = firstItem.text();
//				Elements zzs = firstItem.getElementsByClass("memo");
//				if (!zzs.isEmpty()) {
//					secondItem = zzs.first();
//				} else {
//					zzs = firstItem.getElementsByClass("itemTypeGroup");
//					if (!zzs.isEmpty()) {
//						secondItem = zzs.first();
//					} else {
//						System.out.println("Still not sorted");
//					}
//				}
//			} else {
//				zz = z.nextElementSiblings().get(0);
//			}
//			//						if 
//			//						Element zz2 = z.nextElementSiblings().
//
//			//						System.out.println("t="+t+" //// zz="+zz);
//			if (secondItem != null) {
//				//							System.out.println("second=>"+secondItem);
//				System.out.println("description=||"+secondItem.text()+"||");
//				//+" /// zz2="+zz2);
//			}
//
//			///// probably need a trailing " > p"  ti the .select() to pick up the paragraph immediately
//			///// following the productText class div
//			///// Without this entry for Blueberries SO Organic 150g adds in the phrase
//			///// Plump and Refreshing
//			if (t.equals("Description")) {
//				//							System.out.println("!!Description");
//				Element productInfo = z.nextElementSiblings().get(0); 
//				//							System.out.println("productInfo: {"+productInfo+"}");
//				System.out.println("description=||"+productInfo.text()+"||");
//				description=productInfo.text();
//				Element nutritionInfo = z.nextElementSiblings().get(1);
//				System.out.println("txt="+nutritionInfo.text());
//				if (nutritionInfo.text().indexOf("Nutrition") == 0) {
//					System.out.println("nutritionInfo: {"+nutritionInfo+"}");
//					Element nutritionBlock = nutritionInfo.nextElementSiblings().get(0);
//					caloriesPer100g = getCalories(nutritionBlock);
//					System.out.println("calories="+caloriesPer100g);//{"+nutritionBlock+"}");
//				}
//				//							
//				//							Element nutritionBlock = nutritionInfo.nextElementSiblings().get(0);
//				//							System.out.println("nutritionBlock = ...");//{"+nutritionBlock+"}");
//				//							Elements nutritionDetails = nutritionInfo.nextElementSiblings();
//				//							for (Element c : nutritionDetails) {
//				//								System.out.println("c -> "+c);
//				//							}
//				//Size
//				//
//			}
//			//					}
//			//				}
//
//			//				System.out.println("body="+body);
//			//				for (Element step : body) {
//			//					System.out.println("zz");
//			//					String method = step.select("div.steps h2 div.access").text();
//			//					System.out.println("method="+method);
//			//					
//			//					
//			//				}
//			//unitPrice =
//			//pricePerKilo=
//
//			//kcalPer100g= **KCal**.ifPresent().get();
//			
//			Optional<Integer> kcalPer100g = caloriesPer100g > 0 ? Optional.of(caloriesPer100g)
//					                                            : Optional.empty();
//			this.details = new Details(name, unitPrice, description, kcalPer100g);
//			System.out.println();
//
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//			e.printStackTrace();
//		}
//
//	}
	
	int getCalories(Element nb) {
		Elements elements = nb.select("table[class=nutritionTable]");
		Elements rows = elements.get(0).select("tr");

//		for (Element row : rows) {
//			System.out.println("***row="+row);
//		}

		Element energyRow0 = rows.select("tr[class=tableRow0]").first();
//		System.out.println("energyRow0");

		Elements entries = energyRow0.select("td");
		for (Element entry : entries) {
			String text = entry.text();
			if (text.indexOf("kcal") > 0) {
				return Integer.parseInt(text.substring(0,text.length()-4));
			}
		}
		
		return 0;
	}

//	public void additionalInfo(String description, Optional<Integer> kcal_per_100g) {
//		this.description = description;
//		this.kcal_per_100g = kcal_per_100g;
//	}
//
	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

//	public double getUnitPrice() {
//		return unitPrice;
//	}
//
//	public void setUnitPrice(double unitPrice) {
//		this.unitPrice = unitPrice;
//	}
//
//	public Optional<Integer> getKcal_per_100g() {
//		return kcal_per_100g;
//	}
//
//	public void setKcal_per_100g(Optional<Integer> kcal_per_100g) {
//		this.kcal_per_100g = kcal_per_100g;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}


//}



//class ProductInfo {
//	String productTitle;
//	String webAddress;
//	String relativeAddress;
//	AdditionalInfo additionalInfo;
//	static int count;
//
//	ProductInfo(String productTitle, String webAddress, String relativeAddress) {
//		this.productTitle = productTitle;
//		this.webAddress = webAddress;
//		this.relativeAddress = relativeAddress;
//		count = 0;
//		this.additionalInfo = new AdditionalInfo(count, webAddress, productTitle);
//	}
//}

//class Details {
//	double unitPrice;
//	String pricePerKilo;
//	String description;
//	Optional<String> kcalPer100g;

//	AdditionalInfo(int count, String webAddress, String presumedProductTitle) {
//		try {
//			System.out.println("additionalInfo");
//			this.description = "description-not-initialized";
//			Document productDoc = Jsoup.connect(webAddress).get();
//			//				System.out.println("productTitle="+productTitle+", webAddress="+webAddress);
//
//
//			Element price = productDoc.selectFirst("p[class=pricePerUnit]");
//			unitPrice = Double.parseDouble(price.text().substring(1,price.text().length()-5));
//			System.out.println("pricePerUnit="+unitPrice);
//
//			Element information = productDoc.getElementById("information");
//			//				Elements body = productDoc.getElementsByClass("section");
//			//				Element information = productDoc.select("id#information").first();
//			Element item = information.getAllElements().get(0);
//			//				System.out.println(information.text());
//
//			//System.out.println("#elements="+elements.size());
//			//for (Element item : elements) {
//
//			System.out.println(""+(count++)+". "+presumedProductTitle);//+item);
//			System.out.println(webAddress.substring(75));
//			Element firstItem  = null;
//			Element secondItem  = null;
//
//			Elements zs = item.getElementsByClass("productDataItemHeader");
//			if (zs.isEmpty()) {
//				System.out.println("==>AAA");// {"+productTitle+"}");
//				zs = item.getElementsByClass("itemTypeGroupContainer");
//				if (zs.isEmpty()) {
//					System.out.println("*************Different type of class matching required***");
//				} else {
//					//							System.out.println("////");
//					for (Element z : zs) {
//						//								if (secondItem == null && firstItem != null) {
//						//									secondItem = z;
//						//								}
//						if (firstItem == null) {
//							firstItem = z;
//						}
//
//						//								System.out.println("z->"+z);
//					}
//					//							System.out.println("]]]]");
//				}
//			}
//			//for (Element z : zs ) {
//			Element z = zs.first();
//			//						System.out.println("z -> "+z);
//			String t = z.text();
//			Element zz = null;
//
//			if (firstItem != null) {						
//				t = firstItem.text();
//				Elements zzs = firstItem.getElementsByClass("memo");
//				if (!zzs.isEmpty()) {
//					secondItem = zzs.first();
//				} else {
//					zzs = firstItem.getElementsByClass("itemTypeGroup");
//					if (!zzs.isEmpty()) {
//						secondItem = zzs.first();
//					} else {
//						System.out.println("Still not sorted");
//					}
//				}
//			} else {
//				zz = z.nextElementSiblings().get(0);
//			}
//			//						if 
//			//						Element zz2 = z.nextElementSiblings().
//
//			//						System.out.println("t="+t+" //// zz="+zz);
//			if (secondItem != null) {
//				//							System.out.println("second=>"+secondItem);
//				System.out.println("description=||"+secondItem.text()+"||");
//				//+" /// zz2="+zz2);
//			}
//
//			///// probably need a trailing " > p"  ti the .select() to pick up the paragraph immediately
//			///// following the productText class div
//			///// Without this entry for Blueberries SO Organic 150g adds in the phrase
//			///// Plump and Refreshing
//			if (t.equals("Description")) {
//				//							System.out.println("!!Description");
//				Element productInfo = z.nextElementSiblings().get(0); 
//				//							System.out.println("productInfo: {"+productInfo+"}");
//				System.out.println("description=||"+productInfo.text()+"||");
//				Element nutritionInfo = z.nextElementSiblings().get(1);
//				System.out.println("txt="+nutritionInfo.text());
//				if (nutritionInfo.text().indexOf("Nutrition") == 0) {
//					System.out.println("nutritionInfo: {"+nutritionInfo+"}");
//					Element nutritionBlock = nutritionInfo.nextElementSiblings().get(0);
//					int caloriesPer100g = getCalories(nutritionBlock);
//					System.out.println("calories="+caloriesPer100g);//{"+nutritionBlock+"}");
//				}
//				//							
//				//							Element nutritionBlock = nutritionInfo.nextElementSiblings().get(0);
//				//							System.out.println("nutritionBlock = ...");//{"+nutritionBlock+"}");
//				//							Elements nutritionDetails = nutritionInfo.nextElementSiblings();
//				//							for (Element c : nutritionDetails) {
//				//								System.out.println("c -> "+c);
//				//							}
//				//Size
//				//
//			}
//			//					}
//			//				}
//
//			//				System.out.println("body="+body);
//			//				for (Element step : body) {
//			//					System.out.println("zz");
//			//					String method = step.select("div.steps h2 div.access").text();
//			//					System.out.println("method="+method);
//			//					
//			//					
//			//				}
//			//unitPrice =
//			//pricePerKilo=
//
//			//kcalPer100g= **KCal**.ifPresent().get();
//			System.out.println();
//
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//			e.printStackTrace();
//		}
//
//	}





		//	    for (Element row : rows) {
		//	    	System.out.println("row = "+row);
		//	    	Elements rowItems = row.select("td[class=nutritionLevel1]");
		//	    	for (Element item : rowItems) {
		//	    		System.out.println("item="+item);
		//	    	}
		//	    	
		////	    	if (row.select("th").equals("Energy kcal")) {
		////	    		return Integer.parseInt(row.select("td").get(0).text());	    		
		////	    	}
		//	    	
		//	    	
		////	        if (row.select("td").size() == 8) {
		////	            String iPAddress = row.select("td").get(0).text();
		////	            String port = row.select("td").get(1).text();
		////	            String code = row.select("td").get(2).text();
		////	            String country = row.select("td").get(3).text();
		////	            String anonymity = row.select("td").get(4).text();
		////	            String google = row.select("td").get(5).text();
		////	            String https = row.select("td").get(6).text();
		////	            String lastChecked = row.select("td").get(7).text();
		////	        }
		//
		//	    }

	//	public void display() {
	//		System.out.println("productTitle="+productTitle);
	//		String reducedLink = relativeAddress.substring(61);
	//		String reducedLinkInfo = reducedLink+"("+validatingTerm(reducedList)+")";//+validatingTerm(reducedLink)+")";
	//		String linkTextInfo = productTitle +"("+isSelectedFruit(reducedLink)+")";
	//		//linkText.
	//		String result = String.format("%63s -> %s", reducedLinkInfo, linkTextInfo);
	//		System.out.println(result);
	////		System.out.println("AbsHref: "+absHref+"\n");
	//		//			  System.out.println("href=" +
	//		//					  reducedLink +
	//		//					    "["+ isSelectedFruit(reducedLink)+"]: " +
	//		//			          linkText +
	//		//			            "["+ isSelectedFruit(reducedLink)+"]"
	//		//			            );
	//	}




//}

