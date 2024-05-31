package com.challenge.challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

//this library is helpful to generate getters and setters automatically
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_attributes")
public class ProductAttributesEntity {

    public ProductAttributesEntity(
            Integer productId,
            Integer attributeId,
            String value) {
        this.productId = productId;
        this.attributeId = attributeId;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "attribute_id")
    private Integer attributeId;

    @Column(name = "value")
    private String value;

    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private ProductAttributeEntity name;

    @Transient
    public String getName() {
        return this.name.getName();
    }

}
