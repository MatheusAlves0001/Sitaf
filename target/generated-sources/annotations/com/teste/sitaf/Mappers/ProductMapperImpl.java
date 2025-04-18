package com.teste.sitaf.Mappers;

import com.teste.sitaf.Models.DTOs.ProductDto;
import com.teste.sitaf.Models.RepositoryModel.ProductModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T14:40:54-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductModel toDto(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        ProductModel productModel = new ProductModel();

        return productModel;
    }

    @Override
    public ProductDto toModel(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        return productDto;
    }
}
