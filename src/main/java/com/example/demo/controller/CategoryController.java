package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public Category editCategory(@PathVariable int id, @RequestBody Category category){
        return categoryService.editCategory(id, category);
    }

    @GetMapping("/brand")
    public List<Product> getCategoryProductsByBrand(@RequestParam Map<String, String> params) {
        String brand = params.get("brand");
        return categoryService.getCategoryProductsByBrand(brand);
    }

    @GetMapping("/order")
    public List<Category> orderCategoryProductsByPrice(@RequestParam Map<String, String> params) {
        String order_price = params.get("order_price");
        return categoryService.orderCategoryProductsByPrice(order_price);
    }

    @GetMapping("/price")
    public List<Category> orderCategoryProductsByPriceRange(@RequestParam Map<String, String> params) {
        String price_min = params.get("price_min");
        String price_max = params.get("price_max");
        return categoryService.orderCategoryProductsByPriceRange(price_min, price_max);
    }

}