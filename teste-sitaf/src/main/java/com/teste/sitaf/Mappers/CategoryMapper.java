package com.teste.sitaf.Mappers;

import com.teste.sitaf.Models.DTOs.CategoryDetailDto;
import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryModel toModel(CategoryDto model);
    CategoryDto toDto(CategoryModel model);
    List<CategoryModel> toListModel(List<CategoryDto> model);
    List<CategoryDto> toListDto(List<CategoryModel> model);
    CategoryDetailDto toDetailDto(CategoryModel model);
}
