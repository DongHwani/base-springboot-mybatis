package com.example.practice.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.practice.category.Category;
import com.example.practice.product.domain.support.ProductDomainBuilder;
import com.example.practice.user.User;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;


public class ProductTest extends ProductDomainBuilder {

	@Test
	public void createInstance() {
		//Given & When
		Product item = ProductDomainBuilder.provideProduct();
		
		//Then
		assertThat(item).isEqualTo(Product.builder().productId(1L).build());
	}

	@Test
	public void updateProductTest(){
		//Given
		Product product = ProductDomainBuilder.provideProduct();

		String updateTitle = "수정된 제목";
		String updateContent = "수정된 본문";
		String updateImage = "수정된 이미지";
		int updateMoney = 5000;


		//When
		Product updateProduct = Product.builder()
				.productId(1L)
				.writer(new User())
				.money(new Money(BigInteger.valueOf(updateMoney)))
				.category(Category.builder().categoryId(1L).build())
				.productName(updateTitle)
				.description(updateContent)
				.image(updateImage)
				.build();

		Product updatedProduct = product.update(updateProduct);

		//Then
		assertAll(
				() -> assertThat(updatedProduct.getProductName()).isEqualTo(updateTitle),
				() -> assertThat(updateProduct.getDescription()).isEqualTo(updateContent),
				() -> assertThat(updateProduct.getImage()).isEqualTo(updateImage),
				() -> assertThat(updateProduct.getMoney()).isEqualTo(updateMoney)
		);
	}
}
