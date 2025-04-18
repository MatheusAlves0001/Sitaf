package com.teste.sitaf.Mappers;

import com.teste.sitaf.Models.DTOs.ProductDto;
import com.teste.sitaf.Models.RepositoryModel.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductModel toDto(ProductDto productDto);
    ProductDto toModel(ProductModel productModel);
}
