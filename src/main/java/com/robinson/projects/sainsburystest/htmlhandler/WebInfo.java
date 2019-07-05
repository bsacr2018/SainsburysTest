package com.robinson.projects.sainsburystest.htmlhandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.robinson.projects.sainsburystest.model.Product;

public class WebInfo {
	private String productsWebPage;
	private List<Product> products;

	public WebInfo(String productsWebPage) {
		this.productsWebPage = productsWebPage;
		products = new ArrayList<Product>();
	}

	public List<Product> getProducts() {
		return products;
	}

	public Document establishDocumentFromURL(String url) throws IOException {
		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Document establishDocumentFromHTML(String html, String pseudoAddress) {
		Document productsDocument = Jsoup.parseBodyFragment(html);
		productsDocument.setBaseUri(pseudoAddress);
		return productsDocument;
	}

	public List<Product> parseDocument(Document productsDocument, List<String> validatingTerms, String relevantCategory) throws IOException {
		products = new ArrayList<Product>();
		Element body = productsDocument.body();
		if (body != null) {
			Elements links = body.getElementsByTag("a");
			for (Element link : links) {
				String relativeWebRef = link.attr("href");

				if (relativeWebRef.indexOf(relevantCategory) >= 0) {
					String productTitle = link.text();
					String absoluteWebRef = link.absUrl("href");
					if (isValidProduct(absoluteWebRef, validatingTerms)) {
						Product product = new Product(absoluteWebRef, productTitle);
						products.add(product);
					}
				}
			}
		} else {
			System.err.println("HTML body is null");
			System.exit(-1);
		}

		return products;
	}

	// The minimum requirement for the product to be deemed valid is that
	// one of the validatingTerms list appears somewhere in the product title
	boolean isValidProduct(String characterSequence, List<String> validatingTerms) {
		for (String term : validatingTerms) {
			if (characterSequence.indexOf(term) > 0) {
				return true;
			}
		}
		return false;
	}

}
