package com.challenge.challenge.service;

import com.challenge.challenge.dto.ProductDto;
import com.challenge.challenge.entity.PartEntity;
import com.challenge.challenge.entity.ProductAttributeEntity;
import com.challenge.challenge.entity.ProductAttributesEntity;
import com.challenge.challenge.entity.ProductEntity;
import com.challenge.challenge.repository.PartRepository;
import com.challenge.challenge.repository.ProductAttributeRepository;
import com.challenge.challenge.repository.ProductAttributesRepository;
import com.challenge.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    // Declare repositories imported for all tables needed in this service
    private final ProductRepository productRepository;
    private final PartRepository partRepository;
    private final ProductAttributeRepository productAttributeRepository;
    private final ProductAttributesRepository productAttributesRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, PartRepository partRepository,
            ProductAttributeRepository productAttributeRepository,
            ProductAttributesRepository productAttributesRepository) {
        this.productRepository = productRepository;
        this.partRepository = partRepository;
        this.productAttributeRepository = productAttributeRepository;
        this.productAttributesRepository = productAttributesRepository;
    }

    // Begin of services

    public ProductEntity createProduct(ProductDto productDto) {
        // 1: create product in table product
        ProductEntity product = productRepository.save(new ProductEntity(productDto));

        // 2: create Part of product
        partRepository.save(new PartEntity(productDto, product.getId()));

        // 3: save part attributes
        ProductAttributeEntity createAttribute = productAttributeRepository
                .save(new ProductAttributeEntity(productDto.getProductAttributeName()));
        productAttributesRepository.save(new ProductAttributesEntity(product.getId(), createAttribute.getId(),
                productDto.getProductAttributeValue()));

        return product;
    }

    public ProductEntity getProduct(String id) {
        return productRepository.findById(id).orElseThrow();
    }

}
