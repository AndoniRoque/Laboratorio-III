package com.example.demo.persistance;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface CategoryDao {
    List<Category> findAllCategory();
    Category createCategory(Category cat);
    Category getCategoryById(int id);
    Boolean deleteCategory(int id);
    Category editCategory(int id, Category cat);
    ArrayList<Product> getCategoryProductsByBrand(String band);
    ArrayList<Category> orderCategoryProductsByPrice(String order_price);
    ArrayList<Category> orderCategoryProductsByPriceRange(String price_min, String price_max);
}
