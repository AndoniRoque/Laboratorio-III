package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.persistance.CategoryDao;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDaoImpl implements CategoryDao {

    List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> findAllCategory() {
        
        
        categories.add(new Category(2, "Category 2", "Description 2"));
        return categories;
    }

    @Override
    public Category createCategory(Category cat) {
        categories.add(cat);
        return cat;
    }

}
