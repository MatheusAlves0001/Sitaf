package com.teste.sitaf.API.Controllers;

import com.teste.sitaf.API.DTOs.CategoryDetailDto;
import com.teste.sitaf.API.DTOs.CategoryDto;
import com.teste.sitaf.API.DTOs.CreateCategoryDto;
import com.teste.sitaf.API.DTOs.UpdateCategoryDto;
import com.teste.sitaf.Services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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


    @Operation(summary = "Create a new category", description = "Creates a new category based on the provided data.")
    @PostMapping
    public void create(@Valid @RequestBody CreateCategoryDto model) {
        categoryService.create(model);
    }

    @Operation(summary = "List all categories", description = "Returns a list of all the categories in the system.")
    @GetMapping("list")
    public ResponseEntity<List<CategoryDetailDto>> list() {
        var response = categoryService.GetAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an existing category", description = "Updates an existing category based on the provided data.")
    @PatchMapping()
    public void update(@RequestParam long id, @Valid @RequestBody UpdateCategoryDto model) {
        categoryService.update(id, model);
    }

    @Operation(summary = "Delete a category", description = "Deletes a category based on the provided ID.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

    @Operation(summary = "Get category details", description = "Returns the details of a category based on the provided ID.")
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }
}
