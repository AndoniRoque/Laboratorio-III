package com.example.demo.persistance;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAllProducts();
    Product createProduct(Product product);
    Product getProductById(int id);
    Boolean deleteProduct(int id);
    Product updateProduct(int id, Product prod);
}
