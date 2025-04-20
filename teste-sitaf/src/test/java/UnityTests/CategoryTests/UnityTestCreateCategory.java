package UnityTests.CategoryTests;

import com.teste.sitaf.Mappers.CategoryMapper;
import com.teste.sitaf.API.DTOs.CreateCategoryDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnityTestCreateCategory {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldCreateCategorySuccessfully() {
        // Arrange
        var dto = new CreateCategoryDto();
        dto.setName("New Category");

        // Act
        categoryService.create(dto);

        // Assert
        var captor = ArgumentCaptor.forClass(CategoryModel.class);
        verify(categoryRepository, times(1)).save(captor.capture());

        var savedCategory = captor.getValue();
        assertEquals("New Category", savedCategory.getName());
    }
}
