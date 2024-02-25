package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.persistance.exceptions.NoProductsException;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PorductControllerTest {
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void getProductByIdTest() throws NoProductsException {

        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "5'");

        Product product = new Product("product 1", "description", "category 1", "brand", 99.99, "type", specifications);

        when(productService.getProductById(1)).thenReturn(product);

        Product result = productController.getProductById(1);

        assertEquals(product, result);
    }

    @Test
    public void deleteProductTest() throws NoProductsException{
        when(productService.deleteProduct(1)).thenReturn(true);

        Boolean result = productController.deleteProduct(1);

        assertTrue(result);
    }

    @Test
    public void deleteProductException() throws NoProductsException{
        when(productService.deleteProduct(anyInt())).thenThrow(new NoProductsException("No products with that id, please try again"));

        assertThrows(NoProductsException.class, () -> {
            productController.deleteProduct(1);
        });

        verify(productService, times(1)).deleteProduct(1);
    }
}
