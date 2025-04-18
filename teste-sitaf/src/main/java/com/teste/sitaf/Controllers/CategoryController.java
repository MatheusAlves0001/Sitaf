package com.teste.sitaf.Controllers;

import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Services.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService){
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @PostMapping
    public void Create(@RequestBody CategoryDto model) {
        categoryService.Create(model);
    }

    @GetMapping
    public void List() {

    }

    @PatchMapping
    public void Update() {

    }

    @DeleteMapping
    public void Delete() {

    }

    @GetMapping("/{id}")
    public void Get() {

    }

}
