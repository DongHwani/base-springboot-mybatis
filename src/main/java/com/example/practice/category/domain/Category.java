package com.example.practice.category.domain;

import lombok.*;


@Builder @EqualsAndHashCode(exclude = {"isUse", "categoryName"})
@NoArgsConstructor @Getter
@AllArgsConstructor
public class Category {
	
	private Long categoryId;
	private String categoryCode;
	private String categoryName;
	private boolean isUse;

	public void update(Category updateCategory) {
		this.categoryName = updateCategory.getCategoryName();
		this.isUse = updateCategory.isUse();
	}
}
