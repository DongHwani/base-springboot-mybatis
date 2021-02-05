package com.example.practice.category.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryRepository {

    void save(Category category);

    List<Category> findAll();

    Category findById(Long categoryId);

    void deleteAll();

    void update(Category updateCategory);

    boolean existByCategoryCode(String categoryCode);
}
