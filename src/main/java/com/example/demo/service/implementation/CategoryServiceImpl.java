package com.example.demo.service.implementation;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.CategoryDao;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Boolean deleteCategory(int id){
        return categoryDao.deleteCategory(id);
    }

    @Override 
    public Category editCategory(int id, Category cat){
        return categoryDao.editCategory(id, cat);
    }

    @Override
    public ArrayList<Product> getCategoryProductsByBrand(String brand) {
        return categoryDao.getCategoryProductsByBrand(brand);
    }

    @Override
    public ArrayList<Category> orderCategoryProductsByPrice(String order_price) {
        return categoryDao.orderCategoryProductsByPrice(order_price);
    }

    @Override
    public ArrayList<Category> orderCategoryProductsByPriceRange(String price_min, String price_max) {
        return categoryDao.orderCategoryProductsByPriceRange(price_min, price_max);
    }
}