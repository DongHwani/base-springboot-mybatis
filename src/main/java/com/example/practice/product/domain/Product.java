package com.example.practice.product.domain;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.example.practice.category.domain.Category;
import com.example.practice.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"productName", "image", "description", "registeredDate", "price", "category", "seller"})
public class Product {
	
	private Long productId;
	private String productName;
	private Member seller;
	private String image;
	private Money price;
	private String description;
	private LocalDateTime registeredDate;
	private Category category;


    public Product update(Product updateProduct) {
    	this.productName = updateProduct.productName;
    	this.image = updateProduct.image;
    	this.price = updateProduct.price;
    	this.description = updateProduct.description;
		return this;
    }

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public Member getSeller() {
		return seller;
	}

	public String getImage() {
		return image;
	}

	public BigInteger getMoney() {
		return price.getPrice();
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
