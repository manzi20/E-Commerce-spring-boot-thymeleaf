package com.example.testing.Service;

import com.example.testing.Repository.CategoryRepository;
import com.example.testing.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }


//    public List<Category> listNoChildrenCategories() {
//        List<Category> listNoChildrenCategories = new ArrayList<>();
//
//        List<Category> listEnabledCategories = categoryRepository.findAllEnabled();
//
//        listEnabledCategories.forEach(category -> {
//            Set<Category> children = category.getChildren();
//            if (children == null || children.size() == 0) {
//                listNoChildrenCategories.add(category);
//            }
//        });
//        return listNoChildrenCategories;
//    }
}



