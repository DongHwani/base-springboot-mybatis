package com.example.practice.category.application;

import com.example.practice.category.domain.Category;
import com.example.practice.category.domain.CategoryBuilder;
import com.example.practice.category.domain.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryServiceTest extends CategoryBuilder {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    public void deleteSetup(){
        categoryRepository.deleteAll();
    }

    @Test
    public void save(){
        //Given
        Category category = provideCategoryRandom();

        //When
        Category savedCategory = categoryService.save(category);

        //Then
        assertThat(category).isEqualTo(savedCategory);
    }

    @Test
    public void update(){
        //Given
        Category category = provideCategoryRandom();
        categoryService.save(category);

        //When
        Category updateCategory = provideCategoryRandom();
        category.update(updateCategory);
        Category updatedCategory = categoryService.update(category);

        //When
        assertAll(
                () -> assertThat(category.getCategoryId()).isEqualTo(updatedCategory.getCategoryId()),
                () -> assertThat(category.getCategoryCode()).isEqualTo(updatedCategory.getCategoryCode()),

                () -> assertThat(updateCategory.getCategoryName()).isEqualTo(updatedCategory.getCategoryName()),
                () -> assertThat(updateCategory.isUse()).isEqualTo(updatedCategory.isUse())
        );
    }
}
