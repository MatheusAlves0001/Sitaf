package UnityTests.ProductTests;

import com.teste.sitaf.API.DTOs.ChangeCategoryInProductDto;
import com.teste.sitaf.API.DTOs.UpdateProductDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Models.ProductModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.Services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnityTestUpdateProduct {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldUpdateProductSuccessfully() {
        // Arrange
        long productId = 1L;
        long categoryId = 1L;
        long secondCategoryId = 2L;

        var oldCategory = new CategoryModel();
        oldCategory.setId(categoryId);
        oldCategory.setName("Old Category");

        var newCategory = new CategoryModel();
        newCategory.setId(secondCategoryId);
        newCategory.setName("New category");

        var mockProduct = ProductModel.create("Old Product Name", BigDecimal.valueOf(10.0), oldCategory);

        var updatedDto = new UpdateProductDto();
        updatedDto.setName("Updated Product name");
        updatedDto.setPrice(BigDecimal.valueOf(25.5));

        var newCategoryDto = new ChangeCategoryInProductDto();
        newCategoryDto.setId(secondCategoryId);
        updatedDto.setCategory(newCategoryDto);

        when(productRepository.findOne(any(Specification.class))).thenReturn(Optional.of(mockProduct));
        when(categoryRepository.getReferenceById(secondCategoryId)).thenReturn(newCategory);

        // Act
        productService.update(productId, updatedDto);

        // Assert
        assertEquals("Updated Product name", mockProduct.getName());
        assertEquals(newCategory, mockProduct.getCategory());
        assertEquals(BigDecimal.valueOf(25.5), mockProduct.getPrice());

        verify(productRepository).save(mockProduct);
    }

    @Test
    void shouldThrowWhenProductIdIsInvalid() {
        // Arrange
        long invalidId = 0;
        var dto = new UpdateProductDto();

        // Act & Assert
        var exception = assertThrows(InvalidParameterException.class, () -> productService.update(invalidId, dto));
        assertEquals("Set ProductId please", exception.getMessage());
    }

    @Test
    void shouldThrowWhenProductNotFound() {
        // Arrange
        long productId = 1L;
        var dto = new UpdateProductDto();
        dto.setCategory(new ChangeCategoryInProductDto());

//        when(ProductSpecification.getByProductId(productId))
//                .thenReturn((Specification<ProductModel>) (root, query, cb) -> cb.equal(root.get("id"), productId));

        when(productRepository.findOne(any(Specification.class)))
                .thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                productService.update(productId, dto));
        assertEquals("Product not found", exception.getMessage());
    }
}

