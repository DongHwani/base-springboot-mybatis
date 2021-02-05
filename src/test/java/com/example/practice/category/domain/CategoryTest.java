package com.example.practice.category.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CategoryTest extends CategoryBuilder{

    @ParameterizedTest
    @CsvSource(value = {"1:B101:식음료", "2:B102:가전"}, delimiter = ':')
    public void createInstanceTest(Long categoryId, String categoryCode, String categoryName){
        //Given & When
        Category category = Category.builder()
                .categoryId(categoryId)
                .categoryCode(categoryCode)
                .categoryName(categoryName)
                .build();

        //Then
        assertThat(category).isEqualTo(makeCategoryWhenParameter(categoryId, categoryCode, categoryName));
    }

    @ParameterizedTest
    @CsvSource(value = {"식음료:true", "가전:true"}, delimiter = ':')
    public void updateTest(String categoryName, boolean isUse){
        //Given
        Category category = makeCategoryWhenParameter(2L, "TEST", "노트북");

        //When
        category.update(makeCategoryWhenParameter(categoryName, isUse));

        //Then
        assertAll(
                () -> assertThat(category.getCategoryName()).isEqualTo(categoryName),
                () -> assertThat(category.isUse()).isEqualTo(isUse)
        );
    }

}
