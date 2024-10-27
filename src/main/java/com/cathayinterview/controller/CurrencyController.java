package com.cathayinterview.controller;


import com.cathayinterview.entity.CurrencyEntity;
import com.cathayinterview.model.Currency;
import com.cathayinterview.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @RequestMapping(value = "/currencies", method = RequestMethod.GET)
    public List<CurrencyEntity> addNewCurrencies(){
        return currencyService.findAllCurrencies();
    }

    @RequestMapping(value = "/currency/{code}", method = RequestMethod.GET)
    public String getCurrencyByCode(@PathVariable String code){
        CurrencyEntity currencyEntity = currencyService.findAllByCode(code);
        if(currencyEntity == null){
            return "currency not found";
        }

        return currencyEntity.toString();
    }

    @RequestMapping(value = "/currency", method = RequestMethod.POST)
    public String addNewCurrency(@RequestBody Currency currency){

        if(currencyService.findAllByCode(currency.getCode()) != null){
            return "currency exist already";
        }

        CurrencyEntity newCurrencyEntity = new CurrencyEntity();
        newCurrencyEntity.setCode(currency.getCode());
        newCurrencyEntity.setSymbol(currency.getSymbol());
        newCurrencyEntity.setRate(currency.getRate());
        newCurrencyEntity.setDescription(currency.getDescription());
        newCurrencyEntity.setRate_float(currency.getRate_float());
        currencyService.addNewCurrency(newCurrencyEntity);
        return "add new currency success";
    }

    @RequestMapping(value = "/currency/{code}", method = RequestMethod.PUT)
    public String updateCurrencyByCode(@PathVariable String code,
                                       @RequestBody Currency currency){
        CurrencyEntity currencyEntity = currencyService.findAllByCode(code);
        if(currencyEntity == null){
            return "currency not found";
        }

        currencyEntity.setCode(currency.getCode());
        currencyEntity.setSymbol(currency.getSymbol());
        currencyEntity.setRate(currency.getRate());
        currencyEntity.setDescription(currency.getDescription());
        currencyEntity.setRate_float(currency.getRate_float());
        currencyService.updateCurrency(currencyEntity);
        return "update success";
    }

    @RequestMapping(value = "/currency/{code}", method = RequestMethod.DELETE)
    public String deleteCurrencyByCode(@PathVariable String code){
        CurrencyEntity currencyEntity = currencyService.findAllByCode(code);
        if(currencyEntity == null){
            return "currency not found";
        }

        currencyService.deleteCurrency(code);
        return "delete success";
    }

}
