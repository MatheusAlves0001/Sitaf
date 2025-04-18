package com.teste.sitaf.Controllers;

import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public void Create(@RequestBody CategoryDto model) {
        categoryService.Create(model);
    }

    @GetMapping("list")
    public ResponseEntity<Page<CategoryDto>> List(@RequestParam int page, @RequestParam int size) {

        Page<CategoryDto> response = categoryService.GetAll(page, size);
        return ResponseEntity.ok(response);
    }

    @PatchMapping()
    public void Update(@RequestParam long id, @RequestBody CategoryDto model) {
        categoryService.Update(id, model);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable long id) {
        categoryService.Delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> Get(@PathVariable long id) {
       return ResponseEntity.ok(categoryService.Get(id));
    }
}
