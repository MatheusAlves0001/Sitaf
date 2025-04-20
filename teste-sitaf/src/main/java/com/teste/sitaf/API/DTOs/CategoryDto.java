package com.teste.sitaf.API.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.security.InvalidParameterException;

public class CategoryDto {

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
