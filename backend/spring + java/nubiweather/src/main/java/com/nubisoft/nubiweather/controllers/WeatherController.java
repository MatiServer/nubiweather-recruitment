package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.exceptions.TooManyRequestsException;
import com.nubisoft.nubiweather.models.WeatherData;
import com.nubisoft.nubiweather.services.WeatherService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {

    private final WeatherService weatherService;
    private final Bucket bucket;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
        this.bucket = Bucket
                .builder()
                .addLimit(Bandwidth.simple(50, Duration.ofMinutes(1)))
                .build();
    }

    @GetMapping("/hamburg")
    public WeatherData getWeatherForHamburg() {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return weatherService.getCurrentWeather("Hamburg");
        } else {
            throw new TooManyRequestsException("Zbyt wiele zapytań. Spróbuj ponownie za chwilę.");
        }
    }

    @GetMapping("/hamburg/weekly")
    public List<WeatherData> getWeeklyWeather() {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return weatherService.getWeeklyWeather("Hamburg");
        } else {
            throw new TooManyRequestsException("Zbyt wiele zapytań. Spróbuj ponownie za chwilę.");
        }
    }

    @GetMapping("/gliwice")
    public WeatherData getWeatherForGliwice() {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return weatherService.getCurrentWeather("Gliwice");
        } else {
            throw new TooManyRequestsException("Zbyt wiele zapytań. Spróbuj ponownie za chwilę.");
        }
    }

    @GetMapping("/gliwice/weekly")
    public List<WeatherData> getWeeklyWeatherGliwice() {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return weatherService.getWeeklyWeather("Gliwice");
        } else {
            throw new TooManyRequestsException("Zbyt wiele zapytań. Spróbuj ponownie za chwilę.");
        }
    }
}