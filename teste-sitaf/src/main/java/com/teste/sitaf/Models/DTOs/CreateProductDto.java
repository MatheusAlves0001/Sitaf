package com.teste.sitaf.Models.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateProductDto {

    @NotBlank(message = "The Product name can't be empty")
    private String name;
    @DecimalMin(value = "0.01", inclusive = true, message = "Price should be most bigger then 0.01")
    private BigDecimal price;
    @NotNull(message = "CategoryId is mandatory")
    private long CategoryId;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long categoryId) {
        CategoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
