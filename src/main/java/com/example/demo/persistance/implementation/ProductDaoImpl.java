package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.ProductDao;
import com.example.demo.persistance.CategoryDao;
import com.example.demo.persistance.exceptions.*;

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
        if (categories.isEmpty()){
            throw new IllegalStateException("There are no categories created yet, please create a category before creating a new product.");
        }

        this.category = categories.isEmpty() ? null : categories.get(0);

        this.products = new ArrayList<>();
        // Map<String, String> specifications = new HashMap<>();
        // specifications.put("pulgadas", "50''");
        // products.add(new Product("SmartTv", "SmartTV", category, "Samsung", 100000.00, "TV", specifications));
    }

    @Override
    public List<Product> findAllProducts() throws NoProductsException {

        if (products.size() == 0){
            throw new NoProductsException("There are no products loaded yet.");
        } else {
            return products;
        }

    }

    @Override
    public Product createProduct(Product product) throws NoCategoryException {
        boolean categoryFound = false;

        if(categories.isEmpty()) {
            throw new NoCategoryException("There are no categories loaded yet. Please create a category before creating a new Product.");
        } else {
            for (Category category : categories) {
                if(product.getCategory().equalsIgnoreCase(category.getName())) {
                    product.setId(generateId());
                    products.add(product);
                    category.getProductList().add(product);
                    categoryFound = true;
                }
            }
        }

        //throw exception
        if(!categoryFound) {
            throw new NoCategoryException("The Category doesn't exists. Please create a new category before creating a product for that category.");
        }

        return product;
    }

    private int generateId() {
        return (products.size() == 0) ? 0 : products.get(products.size() - 1).getId() + 1;
    }

    @Override
    public Product getProductById(int id) throws NoProductsException {
        if(products.isEmpty()){
            throw new NoProductsException("The product you are looking for doesn't exist, there are no products loaded yet.")
        } else {
            for (Product product : products) {
                if(product.getId() == id){
                    return product;
                }
            }
        }

        throw new NoProductsException("The product you are looking for couldn't be found, please try again.");
    }

    @Override
    public Boolean deleteProduct(int id) throws NoProductsException {
        if(products.isEmpty()){
            throw new NoProductsException("The product you are looking for doesn't exist, there are no products loaded yet.")
        } else {
            for (Product product : products) {
                if (product.getId() == id) {
                    products.remove(product);
                    System.out.println("The product was deleted successfuly.");
                    return true;
                }
            }
        }
        throw new NoProductsException("The id provided doesn't match with any product loaded. Please try again.");
        System.out.println("The product couldn't be deleted.");
        return false;
    }

    @Override
    public Product updateProduct(int id, Product prod) throws NoProductsException {
        if(products.isEmpty()){
            throw new NoProductsException("The product you are looking for doesn't exist, there are no products loaded yet.")
        } else {
            for (Product product : products) {
                if (product.getId() == id) {
                    product.setName(prod.getName());
                    product.setDescription(prod.getDescription());
                    product.setCategory(prod.getCategory());
                    product.setBrand(prod.getBrand());
                    product.setList_price(prod.getList_price());
                    product.setType(prod.getType());
                    product.setSpecifications(prod.getSpecifications());
                    System.out.println("The product was updated successfuly.");

                    return product;
                }
            }
        }
        throw new NoProductsException("The id provided doesn't match with any product loaded. Please try again.");
        System.out.println("The product couldn't be updated, please try again.");
        return prod;
    }

    @Override
    public List<Product> getProductsByFilter(String name, String brand, String category_name) throws NoProductsException {
        // List of products to return  based on the filters provided.
        List<Product> filteredProducts = new ArrayList<>();


        System.out.println("name " + name);
        System.out.println("brand " + brand);
        System.out.println("category_name " + category_name);
        System.out.println("Lista de productos " + products );

        if(products.isEmpty()){
            throw new NoProductsException("The product you are looking for doesn't exist, there are no products loaded yet.")
        } else {
            for (Product product : products) {
                System.out.println("Hola, entré al loop for.");
                if ((name == null || product.getName().equalsIgnoreCase(name))) {
                    System.out.println("Entró porque el nombre matcheó");
                    if (brand == null || product.getBrand().equalsIgnoreCase(brand)) {
                        System.out.println("Entró porque la marca matcheó");
                        if (category_name == null || product.getCategory().equalsIgnoreCase(category_name)) {
                            System.out.println("End of the line");
                            filteredProducts.add(product);
                            System.out.println("Product found and added successfuly.");
                            //return http reponse
                            return filteredProducts;
                        } else {
                            throw new NoProductsException("There are no products in that category, please try again.");
                        }
                    } else {
                        throw new NoProductsException("There are no products with that brand, please try again.");
                    }
                } else {
                    throw new NoProductsException("There are no products with that name, please try again.");
                }
            }
        }
        throw new NoProductsException("No products matching the filters provided where found, please try again.");        return null;
    }
}
