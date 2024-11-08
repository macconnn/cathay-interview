package com.cathayinterview.repository;

import com.cathayinterview.entity.PriceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<PriceEntity, Long> {
    PriceEntity findPriceByDate(String date);
    void deleteAllByDate(String date);
}
