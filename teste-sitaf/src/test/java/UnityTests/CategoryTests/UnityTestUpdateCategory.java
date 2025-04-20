package UnityTests.CategoryTests;

import com.teste.sitaf.API.DTOs.UpdateCategoryDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnityTestUpdateCategory {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldThrowInvalidParameterExceptionWhenIdIsZeroOrNegative() {
        //Arrange
        var dto = new UpdateCategoryDto();
        dto.setName("New Name");

        //Act
        var ex = assertThrows(InvalidParameterException.class, () -> {
            categoryService.update(0L, dto);
        });

        //Assert
        assertEquals("Set categoryId please", ex.getMessage());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenCategoryDoesNotExist() {
        //Arrange
        long id = 1L;
        UpdateCategoryDto dto = new UpdateCategoryDto();
        dto.setName("New Name");

        when(categoryRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        //Act
        var ex = assertThrows(EntityNotFoundException.class, () -> {
            categoryService.update(id, dto);
        });

        //Assert
        assertEquals("Category not found", ex.getMessage());
    }

    @Test
    void shouldUpdateCategoryNameWhenValidIdAndCategoryExists() {
        //Arrange
        long id = 1L;

        var dto = new UpdateCategoryDto();
        dto.setName("Updated Name");

        var existingCategory = new CategoryModel();
        existingCategory.setId(id);
        existingCategory.setName("Old Name");

        when(categoryRepository.findOne(any(Specification.class))).thenReturn(Optional.of(existingCategory));

        //Act
        categoryService.update(id, dto);

        //Assert
        assertEquals("Updated Name", existingCategory.getName());
        verify(categoryRepository).save(existingCategory);
    }
}
