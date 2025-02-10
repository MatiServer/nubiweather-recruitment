package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service
public class WeatherService {
    private static final String API_KEY = "7fde70277f3e45a1986165818251002";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json?key=";

    public WeatherData getCurrentWeather(String city) {
        String url = BASE_URL + API_KEY + "&q=" + city + "&aqi=no";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        WeatherData weatherData = null;

        if (response.getBody() != null) {
            Map<String, Object> body = response.getBody();
            Map<String, Object> current = (Map<String, Object>) body.get("current");
            Map<String, Object> condition = (Map<String, Object>) current.get("condition");

            String description = (String) condition.get("text");
            String icon = (String) condition.get("icon");
            double temp = (double) current.get("temp_c");

            weatherData = new WeatherData("Today", description, icon, temp);
        }

        return weatherData;
    }
}
