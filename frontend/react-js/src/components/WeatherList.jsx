import { useEffect, useState } from "react";
import { fetchWeather } from "../services/weatherApi";
import WeatherCard from "./WeatherCard";

const WeatherList = () => {
    const [weatherData, setWeatherData] = useState([]);

    useEffect(() => {
        fetchWeather().then(setWeatherData);
    }, []);

    return (
        <div className="grid grid-cols-2 md:grid-cols-3 gap-4 p-4">
            {weatherData.map((day, index) => (
                <WeatherCard key={index} {...day} />
            ))}
        </div>
    );
};

export default WeatherList;
