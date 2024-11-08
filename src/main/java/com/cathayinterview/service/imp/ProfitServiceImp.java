package com.cathayinterview.service.imp;

import com.cathayinterview.entity.PriceEntity;
import com.cathayinterview.service.PriceService;
import com.cathayinterview.service.ProfitService;
import com.cathayinterview.utilities.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProfitServiceImp implements ProfitService {

    @Autowired
    Convert convert;
    @Autowired
    PriceService priceService;

    @Override
    public double compareStartAndEnd(String startTime, String endTime) {
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(startTime);
        if (matcher.matches()) {
            // continue
        } else {
            startTime = convert.convertDateFormat(Double.parseDouble(startTime));
        }

        matcher = pattern.matcher(endTime);
        if (matcher.matches()) {
            // continue
        } else {
            endTime = convert.convertDateFormat(Double.parseDouble(endTime));
        }


        PriceEntity priceEntity = priceService.getPriceFromDate(startTime);
        double startPrice = priceEntity.getPrice();
        priceEntity = priceService.getPriceFromDate(endTime);
        double endPrice = priceEntity.getPrice();

        return endPrice - startPrice;
    }

    @Override
    public double compareStartAndEndRate(String startTime, String endTime) {
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(startTime);
        if (matcher.matches()) {
            // continue
        } else {
            startTime = convert.convertDateFormat(Double.parseDouble(startTime));
        }

        matcher = pattern.matcher(endTime);
        if (matcher.matches()) {
            // continue
        } else {
            endTime = convert.convertDateFormat(Double.parseDouble(endTime));
        }


        PriceEntity priceEntity = priceService.getPriceFromDate(startTime);
        double startPrice = priceEntity.getPrice();
        priceEntity = priceService.getPriceFromDate(endTime);
        double endPrice = priceEntity.getPrice();

        return (endPrice - startPrice) / startPrice;
    }
}
