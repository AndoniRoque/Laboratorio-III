package com.example.demo.model;


import java.util.Map;

public class Product {
    private int id;
    private String name;
    private String description;
    private Category category;
    private String brand;
    private double list_price;
    private String type;
    private Map<String, String> specifications;

    public Product(String name, String description,
                   Category category, String brand, double list_price,
                   String type, Map<String, String> specifications) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.list_price = list_price;
        this.type = type;
        this.specifications = specifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Map<String, String> specifications) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", brand='" + brand + '\'' +
                ", list_price=" + list_price +
                ", type='" + type + '\'' +
                ", specifications=" + specifications +
                '}';
    }
}
