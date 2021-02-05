package com.example.practice.category.domain;

import io.github.benas.randombeans.EnhancedRandomBuilder;

public class CategoryBuilder {


    public static Category makeCategoryWhenParameter(Long categoryId, String categoryCode, String categoryName){
        return Category.builder()
                .categoryId(categoryId)
                .categoryCode(categoryCode)
                .categoryName(categoryName)
                .build();
    }
    public static Category makeCategoryWhenParameter(String categoryName, boolean isUse){
        return Category.builder()
                .categoryName(categoryName)
                .isUse(isUse)
                .build();
    }

    public static Category provideCategoryRandom(){
        return EnhancedRandomBuilder.aNewEnhancedRandom()
                    .nextObject(Category.class, "categoryId");
    }
}
