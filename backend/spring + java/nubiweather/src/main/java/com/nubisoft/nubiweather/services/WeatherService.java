package com.nubisoft.nubiweather.services;

import com.nubisoft.nubiweather.models.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class WeatherService {
    private static final Logger LOGGER = Logger.getLogger(WeatherService.class.getName());
    private static final String API_KEY = "7fde70277f3e45a1986165818251002";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/";

    public WeatherData getCurrentWeather(String city) {
        String url = BASE_URL + "current.json?key=" + API_KEY + "&q=" + city + "&aqi=no";
        LOGGER.info("Fetching current weather from URL: " + url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        if (response.getBody() == null) {
            LOGGER.severe("No response from API!");
            return null;
        }

        Map<String, Object> body = response.getBody();
        Map<String, Object> current = (Map<String, Object>) body.get("current");
        Map<String, Object> condition = (Map<String, Object>) current.get("condition");

        String description = (String) condition.get("text");
        String icon = (String) condition.get("icon");
        double temp = (double) current.get("temp_c");

        return new WeatherData("Today", description, icon, temp);
    }

    public List<WeatherData> getWeeklyWeather(String city) {
        String url = BASE_URL + "forecast.json?key=" + API_KEY + "&q=" + city + "&days=7&aqi=no&alerts=no";
        LOGGER.info("Fetching 7-day forecast from URL: " + url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response;

        try {
            response = restTemplate.getForEntity(url, Map.class);
        } catch (Exception e) {
            LOGGER.severe("Error fetching data from WeatherAPI: " + e.getMessage());
            throw new RuntimeException("Błąd pobierania prognozy 7-dniowej: " + e.getMessage());
        }

        if (response.getBody() == null) {
            LOGGER.severe("No response from API for weekly forecast!");
            throw new RuntimeException("Brak odpowiedzi z WeatherAPI dla prognozy tygodniowej.");
        }

        Map<String, Object> body = response.getBody();
        if (!body.containsKey("forecast")) {
            LOGGER.severe("Response body does not contain 'forecast' key!");
            throw new RuntimeException("Niepoprawna odpowiedź z WeatherAPI: brak klucza 'forecast'.");
        }

        Map<String, Object> forecast = (Map<String, Object>) body.get("forecast");
        List<Map<String, Object>> forecastDays = (List<Map<String, Object>>) forecast.get("forecastday");

        List<WeatherData> weeklyWeather = new ArrayList<>();

        for (Map<String, Object> day : forecastDays) {
            String date = (String) day.get("date");
            Map<String, Object> dayInfo = (Map<String, Object>) day.get("day");
            Map<String, Object> condition = (Map<String, Object>) dayInfo.get("condition");

            String description = (String) condition.get("text");
            String icon = (String) condition.get("icon");
            double temp = (double) dayInfo.get("avgtemp_c");

            weeklyWeather.add(new WeatherData(date, description, icon, temp));
        }

        LOGGER.info("Successfully fetched 7-day forecast for " + city);
        return weeklyWeather;
    }
}
