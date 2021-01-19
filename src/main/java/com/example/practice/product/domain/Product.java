package com.example.practice.product.domain;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.example.practice.category.Category;
import com.example.practice.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"productName", "image", "description", "registeredDate", "money", "category", "writer"})
public class Product {
	
	private Long productId;
	private String productName;
	private User writer;
	private String image;
	private Money money;
	private String description;
	private LocalDateTime registeredDate;
	private Category category;


    public Product update(Product updateProduct) {
    	this.productName = updateProduct.productName;
    	this.image = updateProduct.image;
    	this.money = updateProduct.money;
    	this.description = updateProduct.description;
		return this;
    }

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public User getWriter() {
		return writer;
	}

	public String getImage() {
		return image;
	}

	public int getMoney() {
		return money.getPrice();
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}

	public Category getCategory() {
		return category;
	}
}
