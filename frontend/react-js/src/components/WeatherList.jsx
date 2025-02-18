import { useEffect, useState } from "react";
import WeatherCard from "./WeatherCard";

const WeatherList = () => {
    const [weatherData, setWeatherData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/weather/hamburg/weekly")
            .then((response) => {
                if (!response.ok) throw new Error("Błąd w pobieraniu danych!");
                return response.json();
            })
            .then((data) => {
                setWeatherData(data);
                setLoading(false);
            })
            .catch((err) => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    if (loading) return <p className="text-center text-gray-600 dark:text-gray-400">Ładowanie danych pogodowych...</p>;
    if (error) return <p className="text-red-500 text-center">{error}</p>;

    return (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-7 gap-4 p-4">
            {weatherData.map((day, index) => (
                <WeatherCard key={index} {...day} delay={index * 0.2} />
            ))}
        </div>
    );
};

export default WeatherList;
