package com.teste.sitaf.API.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.security.InvalidParameterException;

public class CreateCategoryDto {
    @NotBlank(message = "The category name can't be empty")
    private String name;

    public void setName(String name){

        if(name == null || name.isEmpty()) throw new InvalidParameterException("Name canot be empty");
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
