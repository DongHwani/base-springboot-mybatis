package com.example.practice.category.infrastructure;

import com.example.practice.category.domain.Category;
import com.example.practice.category.domain.CategoryBuilder;
import com.example.practice.category.domain.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryRepositoryTest extends CategoryBuilder {

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
	public void deleteSetup(){
    	categoryRepository.deleteAll();
	}

    @Test
    public void save() {
        //Given
        Category category = Category.builder()
                .categoryName("식음료")
                .categoryCode("B101")
                .build();
        //When
        categoryRepository.save(category);
        Category savedCategory = categoryRepository.findById(category.getCategoryId());

        //Then
        assertAll(
                () -> assertThat(category).isEqualTo(savedCategory),
                () -> assertThat(savedCategory.isUse()).isTrue()
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"1:7", "3:5", "2:8"}, delimiter = ':')
    public void findAll(int startIdx, int endIdx) {
        //Given
        IntStream.rangeClosed(startIdx, endIdx)
                .forEach(i -> categoryRepository.save(provideCategoryRandom()));

        //When
        List<Category> categories = categoryRepository.findAll();

        //Then
        assertThat(categories).hasSize(endIdx - (startIdx - 1));
    }

    @Test
	public void update(){
    	//Given
		Category saveCategory = provideCategoryRandom();
		categoryRepository.save(saveCategory);

		Category updateCategory = provideCategoryRandom();

		//When
		saveCategory.update(updateCategory);
		categoryRepository.update(saveCategory);
		Category updatedCategory = categoryRepository.findById(saveCategory.getCategoryId());

		//Then
		assertAll(
                () -> assertThat(updateCategory.getCategoryName()).isEqualTo(updatedCategory.getCategoryName()),
                () -> assertThat(updateCategory.isUse()).isEqualTo(updatedCategory.isUse())
        );
	}

	@Test
    public void existByCategoryCode() {
        //Given
        Category category = provideCategoryRandom();
        categoryRepository.save(category);

        //When
        boolean isExisted = categoryRepository.existByCategoryCode(category.getCategoryCode());

        assertThat(isExisted).isTrue();
    }

}
