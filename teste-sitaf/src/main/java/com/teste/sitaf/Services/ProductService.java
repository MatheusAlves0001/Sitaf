package com.teste.sitaf.Services;


import com.teste.sitaf.Mappers.ProductMapper;
import com.teste.sitaf.Models.DTOs.CreateProductDto;
import com.teste.sitaf.Models.DTOs.Filters;
import com.teste.sitaf.Models.DTOs.ProductDto;
import com.teste.sitaf.Models.DTOs.UpdateProductDto;
import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import com.teste.sitaf.Models.RepositoryModel.ProductModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Specifiactions.CategorySpecification;
import com.teste.sitaf.Specifiactions.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private ProductSpecification productSpecification;

    private final CategoryRepository categoryRepository;
    private CategorySpecification categorySpecification;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    public void create(CreateProductDto model){

        var category = categoryRepository.getReferenceById(model.getCategoryId());
        var categoryToCreate = ProductModel.create(model.getName(), model.getPrice(), category);

        productRepository.save(categoryToCreate);
    }

    public Page<ProductDto> getAll(int page, int size, Long categoryId){

        Specification<ProductModel> spec = null;
        if(categoryId != null && categoryId > 0)
            spec = productSpecification.getByCategoryId(categoryId);

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<ProductModel> product = productRepository.findAll(spec, pageable);

        return product.map(productMapper::toDto);
    }

    public void update(long id, UpdateProductDto model){

        if(id <= 0) throw new InvalidParameterException("Set ProductId please");

        var productToChange = productRepository.findOne(productSpecification.getByProductId(id))
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        CategoryModel category = null;
        if(model.getCategory().getId() > 0)
            category = categoryRepository.getReferenceById(model.getCategory().getId());

        productToChange.setName(model.getName());
        productToChange.setCategory(category);
        productToChange.setPrice(model.getPrice());

        productRepository.save(productToChange);
    }

    public void delete(long id){

        var productToDelete = productRepository.findOne(productSpecification.getByProductId(id))
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        productRepository.delete(productToDelete);
    }

    public ProductDto get(long id){

        var product = productRepository.findOne(productSpecification.getByProductId(id))
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return productMapper.toDto(product);
    }

}
