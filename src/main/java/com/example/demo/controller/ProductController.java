package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product prod) {
        return productService.updateProduct(id, prod);
    }
}
