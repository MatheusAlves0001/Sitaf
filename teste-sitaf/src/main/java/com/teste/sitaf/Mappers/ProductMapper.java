package com.teste.sitaf.Mappers;

import com.teste.sitaf.API.DTOs.ProductDto;
import com.teste.sitaf.Models.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(ProductModel productDto);
    ProductModel toModel(ProductDto productModel);
}
