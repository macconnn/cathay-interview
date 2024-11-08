package com.cathayinterview.service.imp;

import com.cathayinterview.entity.PriceEntity;
import com.cathayinterview.model.Price;
import com.cathayinterview.repository.PriceRepository;
import com.cathayinterview.service.PriceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImp implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Transactional
    public void saveData(Price price){
        PriceEntity priceEntity = new PriceEntity();

        priceEntity.setProduct_id(price.getProduct_id());
        priceEntity.setPrice(price.getPrice());
        priceEntity.setDate(price.getDate());
        priceRepository.save(priceEntity);
    }

    @Transactional
    @Override
    public void updateData(PriceEntity priceEntity) {
        priceRepository.save(priceEntity);
    }

    @Transactional
    @Override
    public void deleteDataByDate(String date) {
        priceRepository.deleteAllByDate(date);
    }

    @Override
    public PriceEntity getPriceFromDate(String date){
        return priceRepository.findPriceByDate(date);
    }




}
