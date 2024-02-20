package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.ProductDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findAllProducts() {
        Category category = new Category(3, "SmartTVs", "Televisores smart");
        List<Product> products = new ArrayList<>();
        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "50''");
        products.add(new Product("SmartTv", "SmartTV", category,
                "Samsung", 100000.00, "TV",
   specifications));
        return products;
    }
}
