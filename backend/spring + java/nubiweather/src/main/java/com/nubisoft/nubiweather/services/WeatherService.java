package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    private static final String API_KEY = "7fde70277f3e45a1986165818251002";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/forecast.json?key=";

    public List<WeatherData> getWeeklyWeather(String city) {
        String url = BASE_URL + API_KEY + "&q=" + city + "&days=7&aqi=no&alerts=no";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        List<WeatherData> forecastList = new ArrayList<>();
        if (response.getBody() != null) {
            for (Object obj : response.getBody()) {
                Map<String, Object> day = (Map<String, Object>) obj;

                String date = (String) day.get("date");
                String description = (String) day.get("description");
                String icon = (String) day.get("icon");
                double temp = (double) day.get("temp");

                forecastList.add(new WeatherData(date, description, icon, temp));
            }
        }
        return forecastList;
    }
}
