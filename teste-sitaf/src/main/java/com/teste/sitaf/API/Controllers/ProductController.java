package com.teste.sitaf.API.Controllers;

import com.teste.sitaf.API.DTOs.CreateProductDto;
import com.teste.sitaf.API.DTOs.ProductDto;
import com.teste.sitaf.API.DTOs.UpdateProductDto;
import com.teste.sitaf.Services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Product")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @Operation(summary = "Create a new product", description = "Creates a new product based on the provided data.")
    @PostMapping
    public void create(@Valid @RequestBody CreateProductDto model) {
        productService.create(model);
    }

    @Operation(summary = "List all products", description = "Returns a paginated list of all products, with an optional filter by category.")
    @GetMapping
    public ResponseEntity<Page<ProductDto>> list(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(productService.getAll(page, size, categoryId));
    }

    @Operation(summary = "Update an existing product", description = "Updates an existing product based on the provided data.")
    @PatchMapping
    public void update(@RequestParam long id, @Valid @RequestBody UpdateProductDto model) {
        productService.update(id, model);
    }

    @Operation(summary = "Delete a product", description = "Deletes a product based on the provided ID.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }

    @Operation(summary = "Get product details", description = "Returns the details of a product based on the provided ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable long id) {
        return ResponseEntity.ok(productService.get(id));
    }
}
