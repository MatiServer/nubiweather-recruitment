package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.WeatherData;
import com.nubisoft.nubiweather.services.WeatherService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Buckets;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173") // Zabezpieczenie przed nieautoryzowanym dostępem z innych domen
public class WeatherController {

    private final WeatherService weatherService;
    private final Bucket bucket; // Rate limiting

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
        // Rate limit do 100 zapytań na minutę
        this.bucket = Buckets.withCapacity(100).withRefill(Refill.greedy(100, Duration.ofMinutes(1))).build();
    }

    @GetMapping("/hamburg")
    public WeatherData getWeatherForHamburg() {
        // Zabezpieczenie przed nadużyciem API
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return weatherService.getCurrentWeather("Hamburg");
        } else {
            // Zwracamy błąd 429 jeśli użytkownik przekroczy limit zapytań
            throw new TooManyRequestsException("Zbyt wiele zapytań. Spróbuj ponownie za chwilę.");
        }
    }

    @GetMapping("/hamburg/weekly")
    public List<WeatherData> getWeeklyWeather() {
        // Zabezpieczenie przed nadużyciem API
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return weatherService.getWeeklyWeather("Hamburg");
        } else {
            // Zwracamy błąd 429 jeśli użytkownik przekroczy limit zapytań
            throw new TooManyRequestsException("Zbyt wiele zapytań. Spróbuj ponownie za chwilę.");
        }
    }
}
