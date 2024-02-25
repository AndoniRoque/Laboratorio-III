package com.example.demo.persistance;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.exceptions.NoCategoryException;
import com.example.demo.persistance.exceptions.NoProductsException;
import com.example.demo.persistance.implementation.ProductDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductDaoImplTest {
    private ProductDaoImpl productDao;

    @Mock
    private CategoryDao categoryDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productDao = new ProductDaoImpl(categoryDao);
    }

    @Test
    void createProductTest_NoCategories() {
        when(categoryDao.findAllCategory()).thenReturn(new ArrayList<>());

        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "50''");
        Product product = new Product("product 1", "description 1", "category 1", "brand", 99.99, "type", specifications);

        assertThrows(NoCategoryException.class, () -> productDao.createProduct(product));
    }


}
