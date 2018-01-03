package org.omnifaces.showcase.model;

import static java.util.Arrays.asList;

import java.util.List;

public class Category {

	private String name;
	private List<Product> products;

	public Category() {
		//
	}

	public Category(String name, Product... products) {
		this.name = name;
		this.products = asList(products);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}