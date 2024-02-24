package com.example.demo.service.implementation;

import com.example.demo.model.Product;
import com.example.demo.persistance.ProductDao;
import com.example.demo.persistance.exceptions.NoCategoryException;
import com.example.demo.persistance.exceptions.NoProductsException;
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
    public List<Product> getAllProducts() throws NoProductsException {
        return productDao.findAllProducts();
    }

    @Override
    public Product createProduct(Product product) throws NoCategoryException {
        return productDao.createProduct(product);
    }

    @Override
    public Product getProductById(int id) throws NoProductsException {
        return productDao.getProductById(id);
    }

    @Override
    public Boolean deleteProduct(int id) throws NoProductsException {
        return  productDao.deleteProduct(id);
    }

    @Override
    public Product updateProduct(int id, Product prod) throws NoProductsException {
        return productDao.updateProduct(id, prod);
    }

    @Override
    public List<Product> getProductsByFilter(String name, String brand, String category_name) throws NoProductsException {
        return productDao.getProductsByFilter(name, brand, category_name);
    }
}
