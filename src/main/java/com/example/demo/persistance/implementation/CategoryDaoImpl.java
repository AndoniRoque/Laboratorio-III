package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.persistance.CategoryDao;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDaoImpl implements CategoryDao {

    ArrayList<Category> categories = new ArrayList<>();

    @Override
    public List<Category> findAllCategory() {
        // if categories empty return message
        return categories;
    }

    @Override
    public Category createCategory(Category cat) {
        cat.setId(generateId());
        categories.add(cat);
        return cat;
    }

    private int generateId() {
        if(categories.size() == 0) {
            return 0;
        } else {
            return categories.get(categories.size() - 1).getId() + 1;
        }
    }

    @Override
    public Category getCategoryById(int id){
        for (Category category : categories) {
            if(category.getId() == id) {
                System.out.println(category.getId());
                return category;
            } else {
                System.out.println("id de la categoria en la lista de categorias: " + category.getId());
            }
        }
        System.out.println("Error, category not found for category id: " + id);
        return null;
    }

    @Override
    public Boolean deleteCategory(int id){
        for (Category category : categories) {
            if (category.getId() == id){
                categories.remove(category);
                System.out.println("Se borró la categoría exitosamente.");
                return true;
            }
        }
        System.out.println("No se pudo borrar la categoría");
        return false;
    }

    @Override
    public Category editCategory(int id, Category cat) {
        for (Category category : categories) {
            if (category.getId() == id) {
                category.setName(cat.getName());
                category.setDescription(cat.getDescription());
                System.out.println("Se actualizó la categoría correctamente");
                return category;
            }
        }
        System.out.println("No fue posible actualizar la categoría");
        return cat;
    }

}
