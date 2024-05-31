package com.challenge.challenge.controller;

import com.challenge.challenge.dto.ProductDto;
import com.challenge.challenge.entity.ProductEntity;
import com.challenge.challenge.models.CustomResponse;
import com.challenge.challenge.service.ProductService;
import com.challenge.challenge.util.HandleResponse;
import com.challenge.challenge.util.HandlerExceptions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController extends HandlerExceptions {

    // Declare services imported
    @Autowired
    private ProductService productService;

    // Begin of public services POST, GET...

    @PostMapping()
    public ResponseEntity<CustomResponse<ProductEntity>> createProduct(
            @Valid @RequestBody ProductDto productDto) {
        try {
            return HandleResponse.createResponse(this.productService.createProduct(productDto), "Create Product",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return HandleResponse.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ProductEntity>> getProduct(@PathVariable String id) {
        try {
            return HandleResponse.createResponse(this.productService.getProduct(id), "Product Found", HttpStatus.FOUND);
        } catch (Exception e) {
            return HandleResponse.createResponse(null, e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
