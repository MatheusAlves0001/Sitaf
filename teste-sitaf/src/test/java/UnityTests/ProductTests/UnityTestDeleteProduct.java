package UnityTests.ProductTests;

import com.teste.sitaf.Models.ProductModel;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Services.ProductService;
import com.teste.sitaf.Specifiactions.ProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnityTestDeleteProduct {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductSpecification productSpecification;

    @InjectMocks
    private ProductService productService;

    @Test
    void delete_shouldDeleteProduct_whenProductExists() {
        // Arrange
        long productId = 1L;
        var mockProduct = new ProductModel();

        when(productRepository.findOne(any(Specification.class))).thenReturn(Optional.of(mockProduct));

        // Act
        productService.delete(productId);

        // Assert
        verify(productRepository).delete(mockProduct);
    }

    @Test
    void delete_shouldThrowException_whenProductNotFound() {
        // Arrange
        long productId = 1L;

        when(productRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(EntityNotFoundException.class,
                () -> productService.delete(productId));

        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, Mockito.never()).delete(any(ProductModel.class));
    }
}
