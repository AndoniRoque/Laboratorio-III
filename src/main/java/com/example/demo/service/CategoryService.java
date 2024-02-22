package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category cat);
    Category getCategoryById(int id);
    Boolean deleteCategory(int id);
    Category editCategory(int id, Category cat);
    ArrayList<Product> getCategoryProductsByBrand(String brand);
    ArrayList<Category> orderCategoryProductsByPrice(String order_price);
}
