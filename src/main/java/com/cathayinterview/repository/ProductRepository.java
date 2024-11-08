package com.cathayinterview.repository;

import com.cathayinterview.entity.ProductsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductsEntity, Long> {

}
