package UnityTests.CategoryTests;

import com.teste.sitaf.Repositories.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;

import java.awt.dnd.InvalidDnDOperationException;
import java.security.InvalidParameterException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnityTestDeleteCategory {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldThrowExceptionWhenIdIsInvalid() {
        //Arrange + Act
        var exception = assertThrows(InvalidParameterException.class, () -> {
            categoryService.delete(0L);
        });

        //Assert
        assertEquals("Set categoryId, please", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryHasProducts() {
        //Arrange
        Long id = 1L;

        when(productRepository.exists(any(Specification.class))).thenReturn(true);

        //Act
        var exception = assertThrows(InvalidDnDOperationException.class, () -> {
            categoryService.delete(id);
        });

        //Assert
        assertEquals("Can't delete this Category because have some Product using this.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        //Arrange
        Long id = 1L;

        when(productRepository.exists(any(Specification.class))).thenReturn(false);
        when(categoryRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        //Act
        var exception = assertThrows(EntityNotFoundException.class, () -> {
            categoryService.delete(id);
        });

        //Assert
        assertEquals("Category not found", exception.getMessage());
    }

    @Test
    void shouldDeleteCategorySuccessfully() {
        //Arrange
        Long id = 1L;
        CategoryModel category = new CategoryModel(); // ou mock(CategoryModel.class)

        when(productRepository.exists(any(Specification.class))).thenReturn(false);
        when(categoryRepository.findOne(any(Specification.class))).thenReturn(Optional.of(category));

        //Act
        categoryService.delete(id);

        //Assert
        verify(categoryRepository).delete(category);
    }
}
