package UnityTests.ProductTests;

import com.teste.sitaf.Mappers.ProductMapper;
import com.teste.sitaf.API.DTOs.ProductDto;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Services.ProductService;
import com.teste.sitaf.Specifiactions.ProductSpecification;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Models.ProductModel;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnityTestGetAllProducts {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductSpecification productSpecification;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnPagedProductsWhenCategoryIdIsValid() {
        // Arrange
        int page = 0;
        int size = 5;
        long categoryId = 1L;

        //Specification<ProductModel> mockSpec = (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
//        var pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        var product = ProductModel.create("Product 1", BigDecimal.valueOf(10.0), new CategoryModel());
        var productDto = new ProductDto();
        productDto.setName("Product 1");

        var productPage = new PageImpl<>(List.of(product));

        //when(ProductSpecification.getByCategoryId(categoryId)).thenReturn(mockSpec);
        when(productRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(productPage);
        when(productMapper.toDto(product)).thenReturn(productDto);

        // Act
        var result = productService.getAll(page, size, categoryId);

        // Assert
        assertEquals(1, result.getContent().size());
        assertEquals("Product 1", result.getContent().get(0).getName());
    }

    @Test
    void shouldReturnAllProductsWhenCategoryIdIsNull() {
        // Arrange
        int page = 0;
        int size = 5;
        Long categoryId = null;

        var product = ProductModel.create("Product 1", BigDecimal.valueOf(10.0), new CategoryModel());
        var productDto = new ProductDto();
        productDto.setName("Product 1");

        var productPage = new PageImpl<>(List.of(product));

        when(productRepository.findAll(isNull(Specification.class), any(Pageable.class))).thenReturn(productPage);
        when(productMapper.toDto(product)).thenReturn(productDto);

        // Act
        var result = productService.getAll(page, size, categoryId);

        // Assert
        assertEquals(1, result.getContent().size());
        assertEquals("Product 1", result.getContent().get(0).getName());
    }
}
