package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product getProductById(int id);
    Boolean deleteProduct(int id);
    Product updateProduct(int id, Product prod);
    List<Product>  getProductsByFilter(String name, String brand, String category_name);
}