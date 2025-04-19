package com.teste.sitaf.Controllers;

import com.teste.sitaf.Models.DTOs.CreateProductDto;
import com.teste.sitaf.Models.DTOs.Filters;
import com.teste.sitaf.Models.DTOs.ProductDto;
import com.teste.sitaf.Models.DTOs.UpdateProductDto;
import com.teste.sitaf.Services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Product")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public void create(@RequestBody CreateProductDto model) {
        productService.create(model);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> list(@RequestParam int page, @RequestParam int size, Filters filters) {
       return ResponseEntity.ok(productService.getAll(page,size,filters)) ;
    }

    @PatchMapping
    public void update(@RequestParam long id, @RequestBody UpdateProductDto model) {
        productService.update(id, model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable long id) {
       return ResponseEntity.ok(productService.get(id));
    }
}
