package com.example.demo.persistance;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.implementation.CategoryDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceImplTest {

    private CategoryDaoImpl categoryDao;

    @BeforeEach
    public void setUp() {
        categoryDao = new CategoryDaoImpl();
    }

    @Test
    public void createCategoryTest() {
        Category category = new Category(0, "category 0", "category 0");
        Category createdCategory = categoryDao.createCategory(category);
        assertNotNull(createdCategory);
        assertEquals(category.getName(), createdCategory.getName());
    }

    @Test
    public void getCategoryByIdTest() {
        Category category = new Category(0, "category 0", "category 0");
        categoryDao.createCategory(category);
        Category getCategory = categoryDao.getCategoryById(0);
        assertNotNull(getCategory);
        assertEquals(category.getId(), getCategory.getId());
    }

    @Test
    public void deleteCategoryTest() {
        Category category = new Category(0, "category 0", "category 0");
        categoryDao.createCategory(category);
        assertTrue(categoryDao.deleteCategory(0));
    }

    @Test
    public void editCategoryTest() {
        Category category = new Category(0, "category 0", "category 0");
        categoryDao.createCategory(category);
        Category updateCategory = new Category(0, "Category 0", "Category zero");
        Category editedCategory = categoryDao.editCategory(0, updateCategory);
        assertNotNull(editedCategory);
        assertEquals(updateCategory.getName(), editedCategory.getName());
    }


    @Test
    public void testOrderCategoryProductsByPriceRange_NoProductsInRange() {
        Category category = new Category(1, "Electronics", "Electronics category");
        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "5'");
        Product product = new Product("product", "product desc", "category 0", "brand", 1299.99, "type",specifications);
        Product product2 = new Product("prodct2", "Phone", "category 0", "samsung", 800.0, "type",specifications);
        category.getProductList().add(product);
        category.getProductList().add(product2);

        categoryDao.createCategory(category);

        List<Category> filteredCategories = categoryDao.orderCategoryProductsByPriceRange("1200", "1500");

        assertFalse(filteredCategories.isEmpty());
    }

    @Test
    public void orderCategoryProductsByPriceRangeTest() {
        List<Category> filteredCategories = categoryDao.orderCategoryProductsByPriceRange("700", "900");
        assertTrue(filteredCategories.isEmpty());
    }

}
