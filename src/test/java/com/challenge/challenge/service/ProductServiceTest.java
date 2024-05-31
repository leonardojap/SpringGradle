package com.challenge.challenge.service;

import com.challenge.challenge.dto.ProductDto;
import com.challenge.challenge.entity.ProductAttributeEntity;
import com.challenge.challenge.entity.ProductAttributesEntity;
import com.challenge.challenge.entity.ProductEntity;
import com.challenge.challenge.repository.PartRepository;
import com.challenge.challenge.repository.ProductAttributeRepository;
import com.challenge.challenge.repository.ProductAttributesRepository;
import com.challenge.challenge.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PartRepository partRepository;

    @Mock
    private ProductAttributeRepository productAttributeRepository;

    @Mock
    private ProductAttributesRepository productAttributesRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setInternalProductId("INT-PROD-1234");
        productDto.setInternalName("My Internal Product");
        productDto.setExternalProductId(12345);
        productDto.setInternalPartId("INT-PART-567");
        productDto.setExternalPartId("EXT-PART-890");
        productDto.setProductAttributeName("Color");
        productDto.setProductAttributeValue("Blue");

        when(productRepository.save(any(ProductEntity.class))).thenReturn(new ProductEntity());
        when(productAttributeRepository.save(any(ProductAttributeEntity.class))).thenReturn(new ProductAttributeEntity());
        when(productAttributesRepository.save(any(ProductAttributesEntity.class))).thenReturn(new ProductAttributesEntity());

        ProductEntity productEntity = productService.createProduct(productDto);

        assertNotNull(productEntity);
    }

    @Test
    public void testGetProduct() {
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

        when(productRepository.findById(any(String.class))).thenReturn(Optional.of(new ProductEntity()));

        ProductEntity product = productService.getProduct(id);

        assertNotNull(product);
    }
}