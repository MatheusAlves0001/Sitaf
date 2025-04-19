package com.teste.sitaf.Controllers;

import com.teste.sitaf.Models.DTOs.CategoryDetailDto;
import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Models.DTOs.CreateCategoryDto;
import com.teste.sitaf.Models.DTOs.UpdateCategoryDto;
import com.teste.sitaf.Services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public void create(@RequestBody CreateCategoryDto model) {
        categoryService.create(model);
    }

    @GetMapping("list")
    public ResponseEntity<List<CategoryDetailDto>> list() {
        var response = categoryService.GetAll();
        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public void update(@RequestParam long id, @RequestBody UpdateCategoryDto model) {
        categoryService.update(id, model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable long id) {
       return ResponseEntity.ok(categoryService.get(id));
    }
}
