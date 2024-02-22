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

    private CategoryDao categoryDao;
    private List<Category> categories;
    private Category category;
    private List<Product> products;

    @Autowired
    public ProductDaoImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;

        // Create a list of all the categories.
        this.categories = categoryDao.findAllCategory();

        // Throw exception here for when a Product wants to be created before creating a category.
        this.category = categories.isEmpty() ? null : categories.get(0);

        this.products = new ArrayList<>();
        Map<String, String> specifications = new HashMap<>();
        specifications.put("pulgadas", "50''");
        products.add(new Product("SmartTv", "SmartTV", category, "Samsung", 100000.00, "TV", specifications));
    }

    @Override
    public List<Product> findAllProducts() {
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

    @Override
    public List<Product> getProductsByFilter(String name, String brand, String category_name){
        // List of products to return  based on the filters provided.
        List<Product> filteredProducts = new ArrayList<>();

        // Create aux list of categories and a Category object
        // Since Product has an attribute Category of the type category I need to check
        // the name passed through the url with the Category.getName() method.
        List<Category> allCategories = categoryDao.findAllCategory();
        Category productCategory = null;

        for (Category cat : allCategories) {
            if(cat.getName().equals(category_name)){
                productCategory = cat;
            }
        }

        System.out.println("Lista de productos " + products );
        for(Product product : products) {

            System.out.println("Hola, entré al loop for.");

            if ((name == null || product.getName().contains(name))) {
                System.out.println("Entró porque el nombre matcheó");
                if (brand == null || product.getBrand().contains(brand)) {
                    System.out.println("Entró porque la marca matcheó");
                    if (category_name == null || product.getCategory().getName().equals(category_name)) {
                        System.out.println("End of the line");
                        filteredProducts.add(product);
                        System.out.println("Se encontró un producto y fue agregado exitosamente.");
                    }
                }
            }
        }
        return filteredProducts;
    }

}
