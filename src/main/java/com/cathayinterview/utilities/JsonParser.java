package com.cathayinterview.utilities;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JsonParser {

    private static final Pattern TIME_PATTERN = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");


    public String coindeckParser(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);

        // parse date and time
        String time, date;
        JSONObject timeObject = jsonObject.getJSONObject("time");
        String updated = timeObject.getString("updated");
        String updatedISO = timeObject.getString("updatedISO");

        Matcher timeMatcher = TIME_PATTERN.matcher(updated);
        if(timeMatcher.find()){
            time = timeMatcher.group();
        } else {
            return null;
        }

        Matcher dateMatcher = DATE_PATTERN.matcher(updatedISO);
        if(dateMatcher.find()){
            date = dateMatcher.group().replace("-", "/");
        } else {
            return null;
        }

        String dateTime = String.format("%s %s", date, time);

        JSONObject originalBpi = jsonObject.getJSONObject("bpi");
        JSONObject newBpiJson = new JSONObject();

        Map<String, String> currencyMap = new HashMap<>();
        currencyMap.put("USD", "美元");
        currencyMap.put("GBP", "英鎊");
        currencyMap.put("EUR", "歐元");

        Iterator<String> keys = originalBpi.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject currencyData = originalBpi.getJSONObject(key);

            String code = currencyData.getString("code");
            String rate = currencyData.getString("rate");
            String codeChinese = currencyMap.get(code);

            JSONObject newCurrencyData = new JSONObject();
            newCurrencyData.put("code", code);
            newCurrencyData.put("rate", rate);
            newCurrencyData.put("codeChinese", codeChinese);

            newBpiJson.put(code, newCurrencyData);
        }

        JSONObject resultJson = new JSONObject();
        resultJson.put("time", dateTime);
        resultJson.put("bpi", newBpiJson);

        return resultJson.toString();
    }



}
