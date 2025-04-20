package com.teste.sitaf.Models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.security.InvalidParameterException;

@Entity
@Table(name = "Product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if(name == null || name.isEmpty())
            throw new InvalidParameterException("Name canot be empty");

        this.name = name;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static ProductModel create(String name, BigDecimal price, CategoryModel category){

        if(name == null || name.isEmpty())
            throw new InvalidParameterException("Name canot be empty");

        if(price.compareTo(BigDecimal.valueOf(0.01)) < 0)
            throw new InvalidParameterException("Price should be most bigger then 0.01");

        var product = new ProductModel();
        product.name = name;
        product.price = price;
        product.category = category;

        return product;
    }
}
