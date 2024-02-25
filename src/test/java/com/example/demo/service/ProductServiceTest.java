package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.persistance.ProductDao;
import com.example.demo.persistance.exceptions.NoCategoryException;
import com.example.demo.persistance.exceptions.NoProductsException;
import com.example.demo.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() throws NoProductsException {
        List<Product> productList = new ArrayList<>();
        Map<String, String> specifications1 = new HashMap<>();
        Map<String, String> specifications2 = new HashMap<>();
        specifications1.put("pulgadas", "5''");
        specifications2.put("pulgadas", "75''");

        productList.add(new Product("Product 1", "Product 1", "category 0", "samsung", 999.99, "cellphone", specifications1));
        productList.add(new Product("Product 2", "Product 2", "category 1", "LG", 20000.0, "tv", specifications2));

        when(productDao.findAllProducts()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();

        assertEquals(productList, result);
        verify(productDao, times(1)).findAllProducts();
    }

    @Test
    public void testCreateProduct() throws NoCategoryException {

        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "5'");

        Product product = new Product("Producto 1", "new product", "category 0", "samsung", 999.99, "cellphone", specifications);

        when(productDao.createProduct(product)).thenReturn(product);

        Product createProduct = productService.createProduct(product);

        assertEquals(product, createProduct);
        verify(productDao, times(1)).createProduct(product);


    }

    @Test
    public void testGetProdctById() throws NoProductsException {
        int nonExistentProductId = 999;
        when(productDao.getProductById(nonExistentProductId)).thenThrow(new NoProductsException("There are no products with that id, please try again."));

        assertThrows(NoProductsException.class, () -> productService.getProductById(nonExistentProductId));
        verify(productDao, times(1)).getProductById(nonExistentProductId);
    }
}
