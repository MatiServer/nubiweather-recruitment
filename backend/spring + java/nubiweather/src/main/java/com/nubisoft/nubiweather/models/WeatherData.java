package com.nubisoft.nubiweather.models;

import lombok.Getter;

@Getter
public class WeatherData {
    private String date;
    private String description;
    private String icon;
    private double temp;

    public WeatherData(String date, String description, String icon, double temp) {
        this.date = date;
        this.description = description;
        this.icon = icon;
        this.temp = temp;
    }

}
