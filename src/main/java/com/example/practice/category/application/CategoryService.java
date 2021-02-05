package com.example.practice.category.application;

import com.example.practice.category.domain.Category;
import com.example.practice.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public Category update(Category updateCategory) {
        categoryRepository.update(updateCategory);
        return categoryRepository.findById(updateCategory.getCategoryId());
    }
}
