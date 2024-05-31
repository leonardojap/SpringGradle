package com.challenge.challenge.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductDto {

    @Valid

    @NotNull(message = "internal product Id is null")
    private String internalProductId;

    @NotNull(message = "internal Name is null")
    private String internalName;

    @NotNull(message = "external product Id is null")
    private Integer externalProductId;

    @NotNull(message = "internal Part Id is null")
    private String internalPartId;

    @NotNull(message = " external Part Id is null")
    private String externalPartId;

    @NotEmpty(message = "product Attribute Name is empty")
    private String productAttributeName;

    @NotEmpty(message = "product Attribute Value is empty")
    @NotNull(message = " external Part Id is null")
    private String productAttributeValue;


}
