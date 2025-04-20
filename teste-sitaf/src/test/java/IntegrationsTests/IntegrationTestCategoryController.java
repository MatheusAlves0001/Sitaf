package IntegrationsTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.sitaf.API.DTOs.CreateCategoryDto;
import com.teste.sitaf.API.DTOs.UpdateCategoryDto;
import com.teste.sitaf.Models.CategoryModel;
import com.teste.sitaf.Repositories.CategoryRepository;
import com.teste.sitaf.TesteSitafApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TesteSitafApplication.class)
public class IntegrationTestCategoryController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setup() {
        categoryRepository.deleteAll();
    }

    @Test
    void shouldCreateCategory() throws Exception {
        var dto = new CreateCategoryDto();
        dto.setName("Electronics");

        mockMvc.perform(post("/api/Category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnAllCategories() throws Exception {
        categoryRepository.save(CategoryModel.create("Books"));

        mockMvc.perform(get("/api/Category/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Books"));
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        var category = categoryRepository.save(CategoryModel.create("Old Name"));

        var dto = new UpdateCategoryDto();
        dto.setName("New Name");

        mockMvc.perform(patch("/api/Category?id=" + category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        var updated = categoryRepository.findById(category.getId()).orElseThrow();
        assertEquals("New Name", updated.getName());
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        var category = categoryRepository.save(CategoryModel.create("To Delete"));

        mockMvc.perform(delete("/api/Category/" + category.getId()))
                .andExpect(status().isOk());

        assertFalse(categoryRepository.existsById(category.getId()));
    }

    @Test
    void shouldGetCategoryById() throws Exception {
        var category = categoryRepository.save(CategoryModel.create("One Category"));

        mockMvc.perform(get("/api/Category/" + category.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("One Category"));
    }
}
