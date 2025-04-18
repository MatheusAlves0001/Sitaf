package com.teste.sitaf.Mappers;

import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryModel toModel(CategoryDto categoryDto);
    CategoryDto toDto(CategoryModel categoryModel);
    List<CategoryModel> toListModel(List<CategoryDto> categoryDto);
    List<CategoryDto> toListDto(List<CategoryModel> categoryModel);
}
