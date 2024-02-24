package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.persistance.exceptions.NoCategoryException;
import com.example.demo.persistance.exceptions.NoProductsException;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() throws NoProductsException {
        return productService.getAllProducts();
    }
    
    @PostMapping
    public Product createProduct(@RequestBody Product product) throws NoCategoryException {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) throws NoProductsException {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable int id) throws NoProductsException {
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product prod) throws NoProductsException {
        return productService.updateProduct(id, prod);
    }

    @GetMapping("/filter")
    public List<Product> getProductsByFilter(@RequestParam Map<String, String> params) throws NoProductsException {
        String name = params.get("name");
        String brand = params.get("brand");
        String category_name = params.get("cat");
        return productService.getProductsByFilter(name, brand, category_name);
    }
}
