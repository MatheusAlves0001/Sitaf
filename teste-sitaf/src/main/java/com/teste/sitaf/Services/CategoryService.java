package com.teste.sitaf.Services;

import com.teste.sitaf.Mappers.CategoryMapper;
import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Specifiactions.CategorySpecification;
import com.teste.sitaf.Specifiactions.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

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

    public CategoryDto Get(Long id){

        if(id <= 0)
            throw new InvalidParameterException("Set categoryId, please");

        CategoryModel category = categoryRepository.findOne(CategorySpecification.hasCategory(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        return categoryMapper.toDto(category);
    }

    public void Create(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toModel(categoryDto));
    }

    public Page<CategoryDto> GetAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<CategoryModel> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDto);
    }

    public void Update(long id, CategoryDto categoryDto){

        if(id <= 0)
            throw new InvalidParameterException("Set categoryId please");

        if(categoryDto.getName().isEmpty())
            throw new InvalidParameterException("Set the new name of Category please");

        CategoryModel categoryToChange = categoryRepository.findOne(CategorySpecification.hasCategory(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryToChange.setName(categoryDto.getName());
        categoryRepository.save(categoryToChange);
    }

    public void Delete(Long id){

        if(id <= 0)
            throw new InvalidParameterException("Set categoryId, please");

        Boolean hasSomeProductUsingThisCatId = productRepository.exists(ProductSpecification.hasCategory(id));

        if(hasSomeProductUsingThisCatId)
            throw new InvalidDnDOperationException("Can't delete this Category because have some Product using this.");

        CategoryModel category = categoryRepository.findOne(CategorySpecification.hasCategory(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryRepository.delete(category);
    }
}
