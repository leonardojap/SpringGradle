package com.challenge.challenge.entity;

import jakarta.persistence.*;

//this library is helpful to generate getters and setters automatically
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_attribute")
public class ProductAttributeEntity {

    public ProductAttributeEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
