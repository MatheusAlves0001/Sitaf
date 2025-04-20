package com.teste.sitaf.API.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

public class ProductDto {

//    private long id;
    @NotBlank(message = "The Product name can't be empty")
    private String name;
    @DecimalMin(value = "0.01", inclusive = true, message = "Price should be most bigger then 0.01")
    private BigDecimal price;
    @NotNull(message = "Category is mandatory")
    public CategoryDetailDto category;

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name == null || name.isEmpty()) throw new InvalidParameterException("The Product name can't be empty");
        this.name = name;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public CategoryDetailDto getCategory(){
        return category;
    }

    public void setCategory(CategoryDetailDto category){
        this.category = category;
    }

//    public long getId() {
//       return id;
//    }
//
//   public void setId(long id) {
//       this.id = id;
//    }
}
