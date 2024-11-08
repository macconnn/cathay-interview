package com.cathayinterview.service;


import com.cathayinterview.entity.PriceEntity;
import com.cathayinterview.model.Price;

public interface PriceService {
    void saveData(Price price);
    void updateData(PriceEntity priceEntity);
    void deleteDataByDate(String date);
    PriceEntity getPriceFromDate(String date);
}
