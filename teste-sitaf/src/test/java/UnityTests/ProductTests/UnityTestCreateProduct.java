package UnityTests.ProductTests;

import com.teste.sitaf.API.DTOs.CreateProductDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Models.ProductModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnityTestCreateProduct {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldCreateProductSuccessfully() {
        // Arrange
        var dto = new CreateProductDto();
        dto.setName("Product Test");
        dto.setPrice(BigDecimal.valueOf(99.99));
        dto.setCategoryId(1L);

        var mockCategory = new CategoryModel();
        mockCategory.setId(1L);
        mockCategory.setName("Category Test");

        var mockProduct = ProductModel.create(dto.getName(), dto.getPrice(), mockCategory);

        when(categoryRepository.getReferenceById(dto.getCategoryId())).thenReturn(mockCategory);
        when(productRepository.save(any(ProductModel.class))).thenReturn(mockProduct);

        // Act
        productService.create(dto);

        // Assert
        verify(categoryRepository).getReferenceById(dto.getCategoryId());
        verify(productRepository).save(any(ProductModel.class));
    }
}
