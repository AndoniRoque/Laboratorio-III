package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.util.ResponseHandler;

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
    public ResponseEntity<Object> getAllCategories() {
        if (categoryService.getAllCategories().isEmpty()) {
            return ResponseHandler.response(
                    HttpStatus.NO_CONTENT,
                    categoryService.getAllCategories(),
                    "No categories created yet."
            );
        } else {
            return ResponseHandler.response(
                    HttpStatus.OK,
                    categoryService.getAllCategories(),
                    "Categories retrived successfuly"
            );
        }
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody Category category){
        return ResponseHandler.response(
                HttpStatus.OK,
                categoryService.createCategory(category),
                "Category created successfully."
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id) {
        if(categoryService.getCategoryById(id) == null){
            return ResponseHandler.response(
                    HttpStatus.NOT_FOUND,
                    categoryService.getCategoryById(id),
                    "Category with id " + id + " not found."
            );
        } else {
            return ResponseHandler.response(
                    HttpStatus.OK,
                    categoryService.getCategoryById(id),
                    "Category retrieved successfully."
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id) {
        if(categoryService.deleteCategory(id)) {
            return ResponseHandler.response(
                    HttpStatus.OK,
                    categoryService.deleteCategory(id),
                    "Category deleted successfully."
            );
        } else {
            return ResponseHandler.response(
                    HttpStatus.NOT_FOUND,
                    categoryService.deleteCategory(id),
                    "Category couldn't be found or doesn't exists. Please see logs for more information."
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editCategory(@PathVariable int id, @RequestBody Category category){
        if(categoryService.editCategory(id, category) == null) {
            return ResponseHandler.response(
                    HttpStatus.NOT_FOUND,
                    categoryService.editCategory(id, category),
                    "Category with id " + id + " not found. Please see logs for more information."
            );
        } else {
            return ResponseHandler.response(
                    HttpStatus.OK,
                    categoryService.editCategory(id, category),
                    "Category with id " + id + " updated successfully."
            );
        }
    }

    @GetMapping("/brand")
    public ResponseEntity<Object> getCategoryProductsByBrand(@RequestParam Map<String, String> params) {
        String brand = params.get("brand");

        if(categoryService.getCategoryProductsByBrand(brand) == null){
            return ResponseHandler.response(
                    HttpStatus.NOT_FOUND,
                    categoryService.getCategoryProductsByBrand(brand),
                    "Products by brand: " + brand + " couldn't be retrieved. Please see logs for more information."
            );
        } else {
            return ResponseHandler.response(
                    HttpStatus.OK,
                    categoryService.getCategoryProductsByBrand(brand),
                    "Category products retrieved successfully by brand: " + brand + "."
            );
        }
    }

    @GetMapping("/order")
    public ResponseEntity<Object> orderCategoryProductsByPrice(@RequestParam Map<String, String> params) {
        String order_price = params.get("order_price");

        if(categoryService.orderCategoryProductsByPrice(order_price) == null){
            return ResponseHandler.response(
                    HttpStatus.NOT_FOUND,
                    categoryService.orderCategoryProductsByPrice(order_price),
                    "There are no products loaded yet. See logs for more information."
            );
        } else {
            return ResponseHandler.response(
                    HttpStatus.OK,
                    categoryService.orderCategoryProductsByPrice(order_price),
                    "Products ordered by " + order_price + "ending order."
            );
        }
    }

    @GetMapping("/price")
    public ResponseEntity<Object> orderCategoryProductsByPriceRange(@RequestParam Map<String, String> params) {
        String price_min = params.get("price_min");
        String price_max = params.get("price_max");

        if(categoryService.orderCategoryProductsByPriceRange(price_min, price_max) == null) {
            return ResponseHandler.response(
                    HttpStatus.NOT_FOUND,
                    categoryService.orderCategoryProductsByPriceRange(price_min, price_max),
                    "There are no products loaded yet. See logs for more information."
            );
        } else {
            return ResponseHandler.response(
                    HttpStatus.OK,
                    categoryService.orderCategoryProductsByPriceRange(price_min, price_max),
                    "Products in the range of " + price_min + " and " + price_max + "."
            );
        }
    }

}