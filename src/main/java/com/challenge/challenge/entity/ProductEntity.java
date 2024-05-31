package com.challenge.challenge.entity;

import com.challenge.challenge.dto.ProductDto;
import jakarta.persistence.*;

//this library is helpful to generate getters and setters automatically
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    public ProductEntity(ProductDto productDto) {
        this.internalProductId = productDto.getInternalProductId();
        this.internalName = productDto.getInternalName();
        this.externalProductId = productDto.getExternalProductId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "internal_product_id", length = 50)
    private String internalProductId;

    @Column(name = "internal_name", length = 255)
    private String internalName;

    @Column(name = "external_product_id", length = 15)
    private Integer externalProductId;

    @Column(name = "external_name", length = 255)
    private String externalName;

    @Column(name = "external_description", length = 255)
    private String externalDescription;

    @CreationTimestamp() // Automatically set on creation
    private LocalDateTime created;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany
    @JoinColumn(name = "id")
    private List<PartEntity> parts;

}
