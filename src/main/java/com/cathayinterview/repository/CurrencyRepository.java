package com.cathayinterview.repository;

import com.cathayinterview.entity.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {
    CurrencyEntity findAllByCode(String code);
    void deleteAllByCode(String code);
    List<CurrencyEntity> findAll();
}
