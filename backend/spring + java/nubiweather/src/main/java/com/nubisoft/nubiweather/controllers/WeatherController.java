package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.WeatherData;
import com.nubisoft.nubiweather.services.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/hamburg")
    public WeatherData getWeatherForHamburg() {
        return weatherService.getCurrentWeather("Hamburg");
    }
}
