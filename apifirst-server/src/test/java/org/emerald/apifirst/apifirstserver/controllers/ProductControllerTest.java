package org.emerald.apifirst.apifirstserver.controllers;

import org.emerald.apifirst.apifirstserver.domain.Product;
import org.emerald.apifirst.model.DimensionsDto;
import org.emerald.apifirst.model.ImageDto;
import org.emerald.apifirst.model.ProductCreateDto;
import org.emerald.apifirst.model.ProductPatchDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductControllerTest extends BaseTest {

    @Test
    void testCreateProduct() throws Exception {
        ProductCreateDto newProduct = ProductCreateDto.builder()
                .description("New Product")
                .cost("5.00")
                .price("8.95")
                .categories(List.of("ELECTRONICS"))
                .images(Collections.singletonList(ImageDto.builder()
                        .url("http://example.com/image.jpg")
                        .altText("Image Alt Text")
                        .build()))
                .dimensions(DimensionsDto.builder()
                        .length(10)
                        .width(10)
                        .height(10)
                        .build())
                .build();

        mockMvc.perform(post(ProductController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void listProducts() throws Exception {
        mockMvc.perform(get(ProductController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void getProductById() throws Exception {
        mockMvc.perform(get(ProductController.BASE_URL + "/{productId}", testProduct.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testProduct.getId().toString())));
    }

    @Transactional
    @Test
    void testUpdateProduct() throws Exception {
        var product = productRepository.findAll().iterator().next();
        var productUpdateDto = productMapper.productToUpdateDto(product);
        productUpdateDto.setDescription("Updated Description");

        mockMvc.perform(put(ProductController.BASE_URL + "/{productId}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo("Updated Description")));
    }

    @Transactional
    @Test
    void testPatchProduct() throws Exception {

        Product product = productRepository.findAll().iterator().next();

        ProductPatchDto productPatchDto = productMapper.productToPatchDto(product);

        productPatchDto.setDescription("Updated Description");

        mockMvc.perform(patch(ProductController.BASE_URL + "/{productId}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productPatchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", equalTo("Updated Description")));
    }

    @Test
    void testDeleteProduct() throws Exception {
        ProductCreateDto newProduct = createTestProductCreateDto();
        Product savedProduct = productRepository.save(productMapper.dtoToProduct(newProduct));

        mockMvc.perform(delete(ProductController.BASE_URL + "/{productId}", savedProduct.getId()))
                .andExpect(status().isNoContent());

        assert productRepository.findById(savedProduct.getId()).isEmpty();
    }

    private ProductCreateDto createTestProductCreateDto() {
        return ProductCreateDto.builder()
                .description("New Product")
                .cost("5.00")
                .price("8.95")
                .categories(List.of("ELECTRONICS"))
                .images(Collections.singletonList(ImageDto.builder()
                        .url("http://example.com/image.jpg")
                        .altText("Image Alt Text")
                        .build()))
                .dimensions(DimensionsDto.builder()
                        .length(10)
                        .width(10)
                        .height(10)
                        .build())
                .build();
    }
}
