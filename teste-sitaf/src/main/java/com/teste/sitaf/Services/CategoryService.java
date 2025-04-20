package com.teste.sitaf.Services;

import com.teste.sitaf.Mappers.CategoryMapper;
import com.teste.sitaf.API.DTOs.CategoryDetailDto;
import com.teste.sitaf.API.DTOs.CategoryDto;
import com.teste.sitaf.API.DTOs.CreateCategoryDto;
import com.teste.sitaf.API.DTOs.UpdateCategoryDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Specifiactions.CategorySpecification;
import com.teste.sitaf.Specifiactions.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.security.InvalidParameterException;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
    }

    public CategoryDto get(long id){

        if(id <= 0) throw new InvalidParameterException("Set categoryId, please");

        CategoryModel category = categoryRepository.findOne(CategorySpecification.getByCategoryId(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        return categoryMapper.toDto(category);
    }

    public void create(CreateCategoryDto categoryDto) {

        var category = CategoryModel.create(categoryDto.getName());
        categoryRepository.save(category);
    }

    public List<CategoryDetailDto> GetAll() {

        var categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDetailDto)
                .toList();
    }

    public void update(long id, UpdateCategoryDto categoryDto){

        if(id <= 0) throw new InvalidParameterException("Set categoryId please");

        CategoryModel categoryToChange = categoryRepository.findOne(CategorySpecification.getByCategoryId(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryToChange.setName(categoryDto.getName());
        categoryRepository.save(categoryToChange);
    }

    public void delete(Long id){

        if(id <= 0) throw new InvalidParameterException("Set categoryId, please");

        Boolean hasSomeProductUsingThisCatId = productRepository.exists(ProductSpecification.getByCategoryId(id));

        if(hasSomeProductUsingThisCatId)
            throw new InvalidDnDOperationException("Can't delete this Category because have some Product using this.");

        CategoryModel category = categoryRepository.findOne(CategorySpecification.getByCategoryId(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryRepository.delete(category);
    }
}
