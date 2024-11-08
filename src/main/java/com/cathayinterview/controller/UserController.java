package com.cathayinterview.controller;

import com.cathayinterview.entity.PriceEntity;
import com.cathayinterview.model.Price;
import com.cathayinterview.model.Products;
import com.cathayinterview.service.PriceService;
import com.cathayinterview.service.ProductsService;
import com.cathayinterview.service.ProfitService;
import com.cathayinterview.utilities.Convert;
import com.cathayinterview.utilities.HttpUtility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    ProductsService productsService;
    @Autowired
    PriceService priceService;
    @Autowired
    HttpUtility httpUtility;
    @Autowired
    Convert convert;
    @Autowired
    ProfitService profitService;

    @GetMapping("cathay-api")
    public String updateDataFromCathayApi(){
        String response = httpUtility.postCathayApi();
        JSONObject jsonObject = new JSONObject(response);
        JSONArray dataArray = jsonObject.getJSONArray("Data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);
            String name = dataObject.getString("name");
            String productId = dataObject.getString("id");
            String shortName = dataObject.isNull("shortName") ? null : dataObject.getString("shortName");
            boolean dataGrouping = dataObject.getBoolean("dataGrouping");

            Products products = new Products(productId, name, shortName, dataGrouping);
            productsService.saveData(products);

            JSONArray innerDataArray = dataObject.getJSONArray("data");
            for (int j = 0; j < innerDataArray.length(); j++) {
                JSONArray innerData = innerDataArray.getJSONArray(j);
                double doubleDate = innerData.getDouble(0);
                double doublePrice = innerData.getDouble(1);
                String timestamp = convert.convertDateFormat(doubleDate);

                Price price = new Price(productId, timestamp, doublePrice);
                priceService.saveData(price);
            }
        }
        return httpUtility.postCathayApi();
    }

    @GetMapping("/{date}")
    public double getPriceByDate(@PathVariable String date){
        PriceEntity priceEntity;
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            priceEntity = priceService.getPriceFromDate(date);
        } else {
            priceEntity = priceService.getPriceFromDate(convert.convertDateFormat(Double.parseDouble(date)));
        }

        return priceEntity.getPrice();
    }

    @PutMapping("/{date}/{price}")
    public String updatePriceByDate(@PathVariable String date, @PathVariable double price){
        PriceEntity priceEntity;
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            priceEntity = priceService.getPriceFromDate(date);
        } else {
            priceEntity = priceService.getPriceFromDate(convert.convertDateFormat(Double.parseDouble(date)));
        }

        priceEntity.setPrice(price);
        priceService.updateData(priceEntity);
        return "update success";
    }

    @DeleteMapping("/{date}")
    public String deleteDataByDate(@PathVariable String date){
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches()) {
            priceService.deleteDataByDate(date);
        } else {
            priceService.deleteDataByDate(convert.convertDateFormat(Double.parseDouble(date)));
        }

        return "delete success";
    }

    @PostMapping("/add")
    public String addPrice(@RequestBody Price price){
        price.setDate(convert.convertDateFormat(Double.parseDouble(price.getDate())));
        priceService.saveData(price);
        return "add price success";
    }

    @GetMapping("/risesAndFalls/{startTime}/{endTime}")
    public double getRisesAndFalls(@PathVariable String startTime, @PathVariable String endTime){
        return profitService.compareStartAndEnd(startTime, endTime);
    }

    @GetMapping("/risesAndFallsRate/{startTime}/{endTime}")
    public String getRisesAndFallsRate(@PathVariable String startTime, @PathVariable String endTime){
        double result = profitService.compareStartAndEndRate(startTime, endTime);
        double percentage = result * 100;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(percentage) + "%");
        return df.format(percentage) + "%";
    }



}
