package com.example.practice.product.domain;

import com.example.practice.product.domain.Product;
import com.example.practice.product.domain.Products;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ProductsTest {

	@ParameterizedTest
	@MethodSource("createProducts")
	public void createInstanceTest(List<Product> productList) {
		//Given & When
		Products products = new Products(productList);
		
		//Then
		assertAll(
				() -> assertThat(products).isNotNull(),
				() -> assertThat(products.size()).isEqualTo(productList.size())
		);
		
	}


	private static Stream<Arguments> createProducts(){
		return Stream.of(
				Arguments.of(Arrays.asList(
						Product.builder().productId(1L), Product.builder().productId(2L))
				)
		);
	}
}
