package com.example.practice.product.domain;

import java.util.List;

public class Products {
	
	private final List<Product> products;
	
	public Products(List<Product> products) {
		if(products.size() < 0) {
			throw new IllegalArgumentException();
		}

		this.products = products;
	}


	public int size() {
		return products.size();
	}

}
