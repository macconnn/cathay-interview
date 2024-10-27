package com.cathayinterview.service.imp;

import com.cathayinterview.entity.CurrencyEntity;
import com.cathayinterview.repository.CurrencyRepository;
import com.cathayinterview.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurrencyServiceImp implements CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public CurrencyEntity findAllByCode(String code){
        return currencyRepository.findAllByCode(code);
    }
    @Override
    public void addNewCurrency(CurrencyEntity currencyEntity){
        currencyRepository.save(currencyEntity);
    }
    @Override
    public void updateCurrency(CurrencyEntity currencyEntity){
        currencyRepository.save(currencyEntity);
    }
    @Override
    @Transactional
    public void deleteCurrency(String code){
        currencyRepository.deleteAllByCode(code);
    }
    @Override
    public List<CurrencyEntity> findAllCurrencies(){
        return currencyRepository.findAll();
    }
}
