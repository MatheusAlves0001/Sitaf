package com.teste.sitaf.Models.RepositoryModel;

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
        this.name = name;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }
}
