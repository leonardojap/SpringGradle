package com.challenge.challenge.repository;

import com.challenge.challenge.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {

    List<ProductEntity> findByDeletedFalse();

}
