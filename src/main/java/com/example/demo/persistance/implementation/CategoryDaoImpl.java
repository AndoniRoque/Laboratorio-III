package com.example.demo.persistance.implementation;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.persistance.CategoryDao;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class CategoryDaoImpl implements CategoryDao {

    ArrayList<Category> categories = new ArrayList<>();

    @Override
    public List<Category> findAllCategory() {
        if(categories.isEmpty()) {
            return Collections.emptyList();
        }
        return categories;
    }

    @Override
    public Category createCategory(Category newCategory) {
        for (Category category : categories) {
            if(newCategory.getName().equalsIgnoreCase(category.getName())){
                System.out.println("Category " + newCategory.getName() + " already exists.");
                return category;
            } else {
                newCategory.setId(generateId());
                categories.add(newCategory);
                System.out.println("Category created successfully.");
            }
        }
        return newCategory;
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
        if(categories.size() == 0) {
            System.out.println("The list of categories is empty, please create new categories before ordering them");
        } else {
            for (Category category : categories) {
                if (category.getId() == id) {
                    System.out.println(category.getId());
                    return category;
                } else {
                    System.out.println("Error, category not found for category id: " + id + ".");
                }
            }
        }
        return null;
    }

    @Override
    public Boolean deleteCategory(int id){
        if(categories.size() == 0) {
            System.out.println("The list of categories is empty, please create new categories before ordering them");
        } else {
            for (Category category : categories) {
                if (category.getId() == id) {
                    categories.remove(category);
                    System.out.println("The Category id " + id + " was successfuly deleted.");
                    return true;
                } else {
                    System.out.println("Category not found.");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public Category editCategory(int id, Category cat) {
        if(categories.size() == 0) {
            System.out.println("The list of categories is empty, please create new categories before ordering them");
        } else {
            for (Category category : categories) {
                if (category.getId() == id) {
                    category.setName(cat.getName());
                    category.setDescription(cat.getDescription());
                    System.out.println("The category was successfuly updapted.");
                    return category;
                } else {
                    System.out.println("Category not found.");
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Product> getCategoryProductsByBrand(String brand) {
        // New list to return as products with certain brand.
        ArrayList<Product> brandProducts = new ArrayList<>();

        System.out.println(categories);

        if(categories.size() == 0) {
            System.out.println("The list of categories is empty, please create new categories before ordering them");
        } else {
            for (Category category : categories) {
                for (Product product : category.getProductList()) {
                    if (product.getBrand().equalsIgnoreCase(brand)) {
                        System.out.println("Products with brand: " + brand + " found.");
                        brandProducts.add(product);
                        return brandProducts;
                    } else {
                        System.out.println("No products with brand: " + brand + " were found");
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Category> orderCategoryProductsByPrice(String order_price){
        
        ArrayList<Category> ordered_list = new ArrayList<>();
        System.out.println(order_price);

        if(categories.size() == 0) {
            System.out.println("The list of categories is empty, please create new categories before ordering them");
        } else {
            for (Category category : categories ) {
                // List of producs for each category. 
                ArrayList<Product> productsList = new ArrayList<>(category.getProductList());

                if(productsList.isEmpty()) {
                    System.out.println("There are no products to order in this category.");
                } else {
                    if(order_price.equalsIgnoreCase("asc")) {
                        productsList.sort(Comparator.comparing(Product::getList_price));
                    } else if( order_price.equalsIgnoreCase("desc")) {
                        productsList.sort(Comparator.comparing(Product::getList_price).reversed());
                    }
                    // New category to add the ordered products to, just to not mess with the original categories list.
                    Category orderedCategory = new Category(category.getId(), category.getName(), category.getDescription());
                    orderedCategory.setProductList(productsList);
                    ordered_list.add(orderedCategory);
                    System.out.println("Successfully ordered categoryes in " + order_price + "ending order");
                    return ordered_list;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Category> orderCategoryProductsByPriceRange(String price_min, String price_max) {
        double min = Double.parseDouble(price_min);
        double max = Double.parseDouble(price_max);

        // Results list as to not mess with the original list of categories.
        ArrayList<Category> results = new ArrayList<>();

        System.out.println("Min: "+ min +". Max: " + max);

        if (categories.size() == 0){
            System.out.println("There are no created categories, please create new categories.");
        } else {
            for (Category category : categories) {
                ArrayList<Product> rangeProductsList = new ArrayList<>();
                for (Product product : category.getProductList()) {
                    if(product.getList_price() >= min && product.getList_price() <= max) {
                        rangeProductsList.add(product);
                    }
                }
                Category priceRangeCategory = new Category(category.getId(), category.getName(), category.getDescription());
                priceRangeCategory.setProductList(rangeProductsList);
                results.add(priceRangeCategory);
                System.out.println("This are the products in the range price of " + min + " and " + max + ".");
                return results;
            }
        }
        return null;
    }
}
