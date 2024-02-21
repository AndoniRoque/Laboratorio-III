package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.ProductDao;
import com.example.demo.persistance.CategoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    private final CategoryDao categoryDao;
    ArrayList<Product> products = new ArrayList<>();

    @Autowired
    public ProductDaoImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Product> findAllProducts() {
        List<Category> categories = categoryDao.findAllCategory();

        // Categoria ya creada para agregar como atributo al producto.
        Category category = categories.isEmpty() ? null : categories.get(0);

        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "50''");

        products.add(new Product("SmartTv", "SmartTV", category,
                "Samsung", 100000.00, "TV", specifications));
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        product.setId(generateId());
        products.add(product);
        return product;
    }

    private int generateId() {
        if(products.size() == 0) {
            return 0;
        } else {
            return products.get(products.size() - 1).getId() + 1;
        }
    }

    @Override
    public Product getProductById(int id) {
        for (Product product : products) {
            if(product.getId() == id){
                return product;
            } else {
                System.out.println("null");
            }
        }
        return null;
    }

    @Override
    public Boolean deleteProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                products.remove(product);
                System.out.println("El producto fue borrado con éxito");
                return true;
            }
        }
        System.out.println("El producto no pudo ser borrado. Disculpe las molestias.");
        return false;
    }

    @Override
    public Product updateProduct(int id, Product prod) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(prod.getName());
                product.setDescription(prod.getDescription());
                product.setCategory(prod.getCategory());
                product.setBrand(prod.getBrand());
                product.setList_price(prod.getList_price());
                product.setType(prod.getType());
                product.setSpecifications(prod.getSpecifications());
                System.out.println("El producto fue actualizado correctamente");
                return product;
            }
        }
        System.out.println("El producto no pudo ser actualizado correctamente, por favor inténtelo nuevamente");
        return prod;
    }

}
