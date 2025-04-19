package com.teste.sitaf.Models.RepositoryModel;

import jakarta.persistence.*;

import java.security.InvalidParameterException;
import java.util.List;

@Entity
@Table(name = "Category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductModel> products;

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name.isEmpty()) throw new InvalidParameterException("Name can't be null");
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static CategoryModel create(String name){

        var category = new CategoryModel();
        category.setName(name);

        return category;
    }
}
