package com.example.practice.category;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CategoryTest {

	@Test
	public void createInstanceTest() {
		//Given & When
		Category category = Category.builder()
				.categoryId(1L)
				.categoryName("전시상품")
				.description("전시된 상품")
				.build();
		
		//Then
		assertThat(category).isEqualTo(Category.builder().categoryId(1L).build());
	}
}
