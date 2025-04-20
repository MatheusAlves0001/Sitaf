package IntegrationsTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.sitaf.Mappers.ProductMapper;
import com.teste.sitaf.API.DTOs.ChangeCategoryInProductDto;
import com.teste.sitaf.API.DTOs.CreateProductDto;
import com.teste.sitaf.API.DTOs.UpdateProductDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Models.ProductModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.Repositories.ProductRepository;
import com.teste.sitaf.TesteSitafApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TesteSitafApplication.class)
public class IntegrationTestProductController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    // Criação de um produto para ser utilizado nos testes
    private ProductModel createTestProduct() {
        var category = new CategoryModel();
        category.setName("Test Category");

        categoryRepository.save(category);

        var product = new ProductModel();
        product.setName("Test Product");
        product.setPrice(new BigDecimal("99.99"));
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Test
    void createProductTest() throws Exception {
        // Arrange
        CreateProductDto productDto = new CreateProductDto();
        productDto.setName("New Product");
        productDto.setPrice(new BigDecimal("50.0"));
        productDto.setCategoryId(1L);  // Assume that the category with ID 1 exists in the DB

        // Act
        mockMvc.perform(post("/api/Product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDto)))
                .andExpect(status().isOk());

        //Assert
        assertTrue(productRepository.existsByName("New Product"));
    }

    @Test
    void getProductByIdTest() throws Exception {
        // Arrange
        var product = createTestProduct();

        // Act & Assert
        mockMvc.perform(get("/api/Product/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.price").value("99.99"));
    }

    @Test
    void updateProductTest() throws Exception {
        // Arrange
        var product = createTestProduct();

        var updateDto = new UpdateProductDto();
        updateDto.setName("Updated Product");
        updateDto.setPrice(new BigDecimal("150.0"));

        var categoryToProductDto = new ChangeCategoryInProductDto();
        categoryToProductDto.setId(product.getCategory().getId());

        updateDto.setCategory(categoryToProductDto);

        // Act
        mockMvc.perform(patch("/api/Product")
                        .param("id", String.valueOf(product.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateDto)))
                .andExpect(status().isOk());

        var updatedProduct = productRepository.findById(product.getId()).orElseThrow();

        //Assert
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(new BigDecimal("150.00"), updatedProduct.getPrice());
    }

    @Test
    void deleteProductTest() throws Exception {
        // Arrange
        var product = createTestProduct();

        // Act
        mockMvc.perform(delete("/api/Product/" + product.getId()))
                .andExpect(status().isOk());

        //Assert
        assertFalse(productRepository.existsById(product.getId()));
    }

    @Test
    void listProductsTest() throws Exception {
        // Arrange
        createTestProduct();

        // Act & Assert
        mockMvc.perform(get("/api/Product")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").isNotEmpty());
    }
}
