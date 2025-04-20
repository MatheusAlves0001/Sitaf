package UnityTests.CategoryTests;

import com.teste.sitaf.Mappers.CategoryMapper;
import com.teste.sitaf.API.DTOs.CategoryDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.security.InvalidParameterException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnityTestGetCategory {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldReturnCategoryDto_WhenCategoryExists() {
        // Arrange
        long categoryId = 1L;

        var mockCategory = new CategoryModel();
        mockCategory.setId(categoryId);
        mockCategory.setName("Test Category");

        var mockDto = new CategoryDto();
        mockDto.setName("Test Category");

        when(categoryRepository.findOne(any(Specification.class))).thenReturn(Optional.of(mockCategory));
        when(categoryMapper.toDto(mockCategory)).thenReturn(mockDto);

        // Act
        CategoryDto result = categoryService.get(categoryId);

        // Assert
        assertNotNull(result);
        assertEquals("Test Category", result.getName());
    }

    @Test
    void shouldThrowInvalidParameterException_WhenIdIsInvalid() {
        // Arrange
        long invalidId = 0;

        // Act + Assert
        assertThrows(InvalidParameterException.class, () -> categoryService.get(invalidId));
        verifyNoInteractions(categoryRepository);
    }

    @Test
    void shouldThrowEntityNotFoundException_WhenCategoryDoesNotExist() {
        // Arrange
        long nonExistentId = 999L;

        when(categoryRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(EntityNotFoundException.class, () -> categoryService.get(nonExistentId));
    }
}
