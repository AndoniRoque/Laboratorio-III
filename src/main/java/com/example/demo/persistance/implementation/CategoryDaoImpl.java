package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.persistance.CategoryDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> findAllCategory() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Category 1", "Description 1"));
        categories.add(new Category(2,"Category 2", "Description 2"));
        return categories;
    }

}
