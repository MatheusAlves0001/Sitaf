package com.teste.sitaf.API.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

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

    public void setPrice(BigDecimal price)
    {
        if(price.compareTo(BigDecimal.valueOf(0.01)) < 0)
            throw new InvalidParameterException("Price should be most bigger then 0.01");

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

        if(name == null || name.isEmpty())
            throw new InvalidParameterException("Name canot be empty");

        this.name = name;
    }
}
