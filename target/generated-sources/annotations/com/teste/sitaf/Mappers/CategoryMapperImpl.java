package com.teste.sitaf.Mappers;

import com.teste.sitaf.Models.DTOs.CategoryDto;
import com.teste.sitaf.Models.RepositoryModel.CategoryModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T15:46:18-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryModel toModel(CategoryDto model) {
        if ( model == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setName( model.getName() );

        return categoryModel;
    }

    @Override
    public CategoryDto toDto(CategoryModel model) {
        if ( model == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setName( model.getName() );

        return categoryDto;
    }

    @Override
    public List<CategoryModel> toListModel(List<CategoryDto> model) {
        if ( model == null ) {
            return null;
        }

        List<CategoryModel> list = new ArrayList<CategoryModel>( model.size() );
        for ( CategoryDto categoryDto : model ) {
            list.add( toModel( categoryDto ) );
        }

        return list;
    }

    @Override
    public List<CategoryDto> toListDto(List<CategoryModel> model) {
        if ( model == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( model.size() );
        for ( CategoryModel categoryModel : model ) {
            list.add( toDto( categoryModel ) );
        }

        return list;
    }
}
