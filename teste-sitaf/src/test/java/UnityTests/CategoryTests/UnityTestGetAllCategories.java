package UnityTests.CategoryTests;

import com.teste.sitaf.Mappers.CategoryMapper;
import com.teste.sitaf.API.DTOs.CategoryDetailDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnityTestGetAllCategories {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldReturnCategoryDetailDtoList() {
        // Arrange
        var category1 = new CategoryModel();
        category1.setId(1L);
        category1.setName("Category 1");

        var category2 = new CategoryModel();
        category2.setId(2L);
        category2.setName("Category 2");

        var mockCategories = List.of(category1, category2);

        var dto1 = new CategoryDetailDto();
        dto1.setId(1L);
        dto1.setName("Category 1");

        var dto2 = new CategoryDetailDto();
        dto2.setId(2L);
        dto2.setName("Category 2");

        when(categoryRepository.findAll()).thenReturn(mockCategories);
        when(categoryMapper.toDetailDto(category1)).thenReturn(dto1);
        when(categoryMapper.toDetailDto(category2)).thenReturn(dto2);

        // Act
        var result = categoryService.GetAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        assertEquals("Category 2", result.get(1).getName());
    }

    @Test
    void shouldReturnEmptyListWhenNoCategories() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(List.of());

        // Act
        var result = categoryService.GetAll();

        // Assert
        assertTrue(result.isEmpty());
    }
}
