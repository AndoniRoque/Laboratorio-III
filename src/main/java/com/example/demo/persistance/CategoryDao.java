package com.example.demo.persistance;

import com.example.demo.model.Category;
import java.util.List;

public interface CategoryDao {
    List<Category> findAllCategory();
    Category createCategory(Category cat);
    Category getCategoryById(int id);
    Boolean deleteCategory(int id);
}
