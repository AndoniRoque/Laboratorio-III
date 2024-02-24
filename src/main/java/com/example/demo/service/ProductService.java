package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.persistance.exceptions.NoCategoryException;
import com.example.demo.persistance.exceptions.NoProductsException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts() throws NoProductsException;
    Product createProduct(Product product) throws NoCategoryException;
    Product getProductById(int id) throws NoProductsException;
    Boolean deleteProduct(int id) throws NoProductsException;
    Product updateProduct(int id, Product prod) throws NoProductsException;
    List<Product> getProductsByFilter(String name, String brand, String category_name) throws NoProductsException;
}