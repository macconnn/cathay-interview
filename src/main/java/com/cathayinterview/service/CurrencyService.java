package com.cathayinterview.service;

import com.cathayinterview.entity.CurrencyEntity;
import com.cathayinterview.model.Currency;
import com.cathayinterview.service.imp.CurrencyServiceImp;

import java.util.List;

public interface CurrencyService{
    CurrencyEntity findAllByCode(String code);
    void addNewCurrency(CurrencyEntity currencyEntity);
    void updateCurrency(CurrencyEntity currency);
    void deleteCurrency(String code);
    List<CurrencyEntity> findAllCurrencies();
}
