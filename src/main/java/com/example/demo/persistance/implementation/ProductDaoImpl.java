package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.ProductDao;
import com.example.demo.persistance.CategoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    private final CategoryDao categoryDao;
    List<Product> products = new ArrayList<>();

    @Autowired
    public ProductDaoImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Category> categories = categoryDao.findAllCategory();

        // Categoria ya creada para agregar como atributo al producto.
        Category category = categories.isEmpty() ? null : categories.get(0);

        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "50''");

        products.add(new Product("SmartTv", "SmartTV", category,
                "Samsung", 100000.00, "TV", specifications));
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product getProductById(int id) {
        for (Product product : products) {
            if(product.getId() == id){
                return product;
            } else {
                System.out.println("null");
            }
        }
        return null;
    }
    
}
