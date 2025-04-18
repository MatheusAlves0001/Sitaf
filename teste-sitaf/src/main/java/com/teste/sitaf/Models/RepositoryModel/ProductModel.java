package com.teste.sitaf.Models.RepositoryModel;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    public String Name;
    public Long CategoryID;
    public BigDecimal Price;

    @ManyToOne
    @JoinColumn(name = "Category_Id")
    public CategoryModel Category;
}
