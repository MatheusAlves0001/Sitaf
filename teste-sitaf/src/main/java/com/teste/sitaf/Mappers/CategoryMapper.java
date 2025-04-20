package com.teste.sitaf.Mappers;

import com.teste.sitaf.API.DTOs.CategoryDetailDto;
import com.teste.sitaf.API.DTOs.CategoryDto;
import com.teste.sitaf.Models.CategoryModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryModel toModel(CategoryDto model);
    CategoryDto toDto(CategoryModel model);
    List<CategoryModel> toListModel(List<CategoryDto> model);
    List<CategoryDto> toListDto(List<CategoryModel> model);
    CategoryDetailDto toDetailDto(CategoryModel model);
}
