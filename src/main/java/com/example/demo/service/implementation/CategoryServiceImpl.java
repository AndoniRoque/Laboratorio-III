package com.example.demo.service.implementation;

import com.example.demo.model.Category;
import com.example.demo.persistance.CategoryDao;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAllCategory();
    }

    @Override
    public Category createCategory(Category cat) {
        return categoryDao.createCategory(cat);
    }

    @Override
    public Category getCategoryById(int id){
        return categoryDao.getCategoryById(id);
    }
    
}