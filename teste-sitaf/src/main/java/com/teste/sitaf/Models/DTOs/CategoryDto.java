package com.teste.sitaf.Models.DTOs;

import jakarta.validation.constraints.NotBlank;

public class CategoryDto {

    @NotBlank(message = "The category name can't be empty")
    public String Name;
}
