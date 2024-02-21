package com.example.demo.service.implementation;

import com.example.demo.model.Product;
import com.example.demo.persistance.ProductDao;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAllProducts();
    }

    @Override
    public Product createProduct(Product product) {
        return productDao.createProduct(product);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public Boolean deleteProduct(int id) {
        return  productDao.deleteProduct(id);
    }

    @Override
    public Product updateProduct(int id, Product prod) {
        return productDao.updateProduct(id, prod);
    }

}
