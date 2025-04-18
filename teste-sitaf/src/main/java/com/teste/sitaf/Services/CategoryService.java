package com.teste.sitaf.Services;

import com.teste.sitaf.Mappers.CategoryMapper;
import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Specifiactions.CategorySpecification;
import com.teste.sitaf.Specifiactions.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
    }

    public CategoryDto Get(Long id){

        if(id <= 0)
            throw new InvalidParameterException("Set categoryId, please");

        var spec = CategorySpecification.hasCategory(id);
        Optional<CategoryModel> category = categoryRepository.findOne(spec);

        return categoryMapper.toDto(category.get());
    }

    public void Create(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toModel(categoryDto));
    }

    public List<CategoryDto> GetAll(Integer page, Integer size) {

        var pageable = PageRequest.of(page, size, Sort.by("Name").ascending());
        var categoryModels = categoryRepository.findAll(pageable).stream().toList();
        return categoryMapper.toListDto(categoryModels);
    }

    public void Update(Long id, CategoryDto categoryDto){

        if(id <= 0)
            throw new InvalidParameterException("Set categoryId, please");

        var spec = CategorySpecification.hasCategory(id);
        CategoryModel categoryToChange = categoryRepository.findOne(spec)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryToChange.Name = categoryDto.Name;

        categoryRepository.save(categoryToChange);
    }

    public void Delete(Long id){

        if(id <= 0)
            throw new InvalidParameterException("Set categoryId, please");

        var productSpec = ProductSpecification.hasCategory(id);
        Boolean hasSomeProductUsingThisCatId = productRepository.exists(productSpec);

        if(hasSomeProductUsingThisCatId)
            throw new InvalidDnDOperationException("Can't delete this Category because have some Product using this.");

        var categorySpec = CategorySpecification.hasCategory(id);
        Optional<CategoryModel> category = categoryRepository.findOne(categorySpec);

        categoryRepository.delete(category.get());
    }
}
