package com.challenge.challenge.controller;

import com.challenge.challenge.dto.ProductDto;
import com.challenge.challenge.entity.ProductEntity;
import com.challenge.challenge.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        // Configura tus objetos de prueba aquí
    }

    @Test
    public void testCreateProduct() throws Exception {

        ProductDto productDto = new ProductDto();
        productDto.setInternalProductId("INT-PROD-1234");
        productDto.setInternalName("My Internal Product");
        productDto.setExternalProductId(12345);
        productDto.setInternalPartId("INT-PART-567");
        productDto.setExternalPartId("EXT-PART-890");
        productDto.setProductAttributeName("Color");
        productDto.setProductAttributeValue("Blue");

        ProductEntity productEntity = new ProductEntity();

        Mockito.when(productService.createProduct(Mockito.any(ProductDto.class))).thenReturn(productEntity);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetProduct() throws Exception {
        String id = "1";
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setInternalProductId("external");
        productEntity.setInternalName("external");
        productEntity.setExternalProductId(1035);
        productEntity.setExternalName("external");
        productEntity.setExternalDescription(null);
        productEntity.setCreated(LocalDateTime.parse("2024-04-05T14:07:33", DateTimeFormatter.ISO_DATE_TIME));
        productEntity.setDeleted(true);
        productEntity.setParts(new ArrayList<>());
        Mockito.when(productService.getProduct(id)).thenReturn(productEntity);
        mockMvc.perform(get("/product/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }
}