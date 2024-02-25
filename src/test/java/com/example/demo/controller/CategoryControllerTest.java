package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.util.ResponseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        when(categoryService.getAllCategories()).thenReturn(new ArrayList<>());

        ResponseEntity<Object> responseEntity = categoryController.getAllCategories();

//        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        Map<String, Object> expectedBody = new HashMap<>();
        expectedBody.put("data", new ArrayList<>());
        expectedBody.put("message", "No categories created yet.");
        expectedBody.put("status", HttpStatus.NO_CONTENT.value());
        assertEquals(expectedBody, responseEntity.getBody());
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Category 1", "Category 1"));
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<Object> responseEntity = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<String, Object> responseBody = (Map<String, Object>)  responseEntity.getBody();
        assertNotNull(responseBody);

        assertEquals("Categories retrived successfuly", responseBody.get("message"));

        List<Category> retrievedCategories = (List<Category>) responseBody.get("data");
        assertNotNull(retrievedCategories);
        assertEquals(categories.get(0).getId(), retrievedCategories.get(0).getId());
        assertEquals(categories.get(0).getName(), retrievedCategories.get(0).getName());
        assertEquals(categories.get(0).getDescription(), retrievedCategories.get(0).getDescription());
    }
}
