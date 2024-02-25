package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.CategoryDao;
import com.example.demo.service.implementation.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void getAllCategoriesTest() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(0, "Category 0", "Category 0 description"));
        categories.add(new Category(1, "Category 1", "Category 1 description"));

        when(categoryDao.findAllCategory()).thenReturn(categories);

        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryDao);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(categories.size(), result.size());
        for (int i = 0; i < categories.size(); i++){
            assertEquals(categories.get(i), result.get(i));
        }
    }

    @Test
    public void testOrderCategoryProductsByPriceRange() {
        // Mock data
        Category category1 = new Category(1, "Category 1", "Description 1");
        Category category2 = new Category(2, "Category 2", "Description 2");

        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "5'");

        Product product1 = new Product("Product 1","product description", "Category 1", "Brand 1", 10.0, "type", specifications);
        Product product2 = new Product("Product 2","product description", "Category 1", "Brand 2", 20.0, "type", specifications);
        Product product3 = new Product("Product 3","product description", "Category 2", "Brand 3", 30.0, "type", specifications);
        Product product4 = new Product("Product 4","product description", "Category 2", "Brand 1", 40.0, "type", specifications);

        category1.getProductList().addAll(Arrays.asList(product1, product2));
        category2.getProductList().addAll(Arrays.asList(product3, product4));

        List<Category> mockCategories = Arrays.asList(category1, category2);

        // Mock behavior
        when(categoryDao.orderCategoryProductsByPriceRange("10", "30")).thenReturn(new ArrayList<>(Arrays.asList(category1, category2)));

        // Test
        List<Category> result1 = categoryService.orderCategoryProductsByPriceRange("10", "30");
        List<Category> result2 = categoryService.orderCategoryProductsByPriceRange("30", "50");

        assertEquals(2, result1.size());
        assertEquals(0, result2.size());

        assertEquals(product1, result1.get(0).getProductList().get(0));
        assertEquals(product2, result1.get(0).getProductList().get(1));
    }
}
