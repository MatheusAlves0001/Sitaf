package UnityTests.ProductTests;

import com.teste.sitaf.Mappers.ProductMapper;
import com.teste.sitaf.API.DTOs.ProductDto;
import com.teste.sitaf.Specifiactions.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import com.teste.sitaf.Models.ProductModel;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnityTestGetProduct {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductSpecification productSpecification;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductDtoWhenProductExists() {
        // Arrange
        long id = 1L;
        ProductModel mockProduct = new ProductModel();
        var expectedDto = new ProductDto();

        when(productRepository.findOne(any(Specification.class))).thenReturn(Optional.of(mockProduct));
        when(productMapper.toDto(mockProduct)).thenReturn(expectedDto);

        // Act
        ProductDto result = productService.get(id);

        // Assert
        assertEquals(expectedDto, result);
        verify(productMapper).toDto(mockProduct);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        // Arrange
        long id = 999L;

        when(productRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        // Act
        var thrown = assertThrows(EntityNotFoundException.class, () -> {
            productService.get(id);
        });

        //Assert
        assertEquals("Product not found", thrown.getMessage());
        verify(productMapper, never()).toDto(any());
    }
}
