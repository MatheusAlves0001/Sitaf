package com.teste.sitaf.Models.RepositoryModel;

import com.teste.sitaf.Mappers.CategoryMapper;
import com.teste.sitaf.Mappers.ProductMapper;
import jakarta.persistence.*;
import java.math.BigDecimal;

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

    private Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

        var product = new ProductModel();
        product.name = name;
        product.price = price;
        product.category = category;

        return product;
    }
}
