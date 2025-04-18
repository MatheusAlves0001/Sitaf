package com.teste.sitaf.Mappers;

import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T04:07:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryModel toModel(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.Name = categoryDto.Name;

        return categoryModel;
    }

    @Override
    public CategoryDto toDto(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.Name = categoryModel.Name;

        return categoryDto;
    }

    @Override
    public List<CategoryModel> toListModel(List<CategoryDto> categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        List<CategoryModel> list = new ArrayList<CategoryModel>( categoryDto.size() );
        for ( CategoryDto categoryDto1 : categoryDto ) {
            list.add( toModel( categoryDto1 ) );
        }

        return list;
    }

    @Override
    public List<CategoryDto> toListDto(List<CategoryModel> categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categoryModel.size() );
        for ( CategoryModel categoryModel1 : categoryModel ) {
            list.add( toDto( categoryModel1 ) );
        }

        return list;
    }
}
