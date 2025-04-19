package com.teste.sitaf.Models.DTOs;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryDto {
    @NotBlank(message = "The category name can't be empty")
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
