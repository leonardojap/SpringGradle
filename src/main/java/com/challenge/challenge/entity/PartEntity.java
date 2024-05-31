package com.challenge.challenge.entity;

import com.challenge.challenge.dto.ProductDto;
import jakarta.persistence.*;

//this library is helpful to generate getters and setters automatically
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "part")
public class PartEntity {

    public PartEntity(ProductDto productDto, Integer productId) {
        this.internalPartId = productDto.getInternalPartId();
        this.externalPartId = productDto.getExternalPartId();
        this.productId = productId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "internal_part_id")
    private String internalPartId;

    @Column(name = "external_part_id")
    private String externalPartId;

    @Column(name = "external_country_of_origin")
    private String externalCountryOfOrigin;

    @Column(name = "external_primary_material")
    private String externalPrimaryMaterial;

    @Column(name = "external_lead_time")
    private Integer externalLeadTime;

    @Column(name = "product_id")
    private Integer productId;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<ProductAttributesEntity> attributes;

    // custom getter to return a list of attributes
    @Transient
    public List<Object> getAttributes() {

        List<Object> listAttributes = new ArrayList<>();

        this.attributes.forEach((attribute) -> {
            Map<String, String> mapAttribute = new HashMap<>();
            mapAttribute.put("id", String.valueOf(attribute.getAttributeId()));
            mapAttribute.put("name", attribute.getName());
            mapAttribute.put("value", attribute.getValue());
            listAttributes.add(mapAttribute);
        });

        return listAttributes;
    }

}
