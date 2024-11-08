package com.cathayinterview.service.imp;

import com.cathayinterview.entity.ProductsEntity;
import com.cathayinterview.model.Products;
import com.cathayinterview.repository.ProductRepository;
import com.cathayinterview.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImp implements ProductsService {
    @Autowired
    ProductRepository productRepository;

    public void saveData(Products products){
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setProduct_id(products.getProduct_id());
        productsEntity.setName(products.getName());
        productsEntity.setData_grouping(products.isData_grouping());
        productsEntity.setShort_name(products.getShort_name());

        productRepository.save(productsEntity);
    }
}
