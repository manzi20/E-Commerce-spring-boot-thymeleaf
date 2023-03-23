package com.example.testing.Service;

import com.example.testing.model.Category;
import com.example.testing.model.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category saveCategory( Category category);



    Category getCategoryById(Long id);

    Category updateCategory(Category category);

    void deleteCategoryById(Long id);

}
