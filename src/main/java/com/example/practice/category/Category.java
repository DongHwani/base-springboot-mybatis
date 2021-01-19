package com.example.practice.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Builder @EqualsAndHashCode(exclude = {"categoryName", "description"})
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	private Long categoryId;
	private String categoryName;
	private String description;

}
