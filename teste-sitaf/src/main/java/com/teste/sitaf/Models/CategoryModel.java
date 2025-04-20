package com.teste.sitaf.Models;

import jakarta.persistence.*;

import java.security.InvalidParameterException;
import java.util.List;

@Entity
@Table(name = "Category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductModel> products;

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name == null || name.isEmpty()) throw new InvalidParameterException("Name canot be empty");
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static CategoryModel create(String name){

        if(name == null || name.isEmpty()) throw new InvalidParameterException("Name canot be empty");

        var category = new CategoryModel();
        category.setName(name);

        return category;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
