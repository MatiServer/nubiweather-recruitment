import { useEffect, useState } from "react";
import nubisoftLogo from "./assets/nubisoft.svg";
import WeatherCard from "./components/WeatherCard";

function App() {
    const [weather, setWeather] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch("http://localhost:8080/api/weather/hamburg")
            .then((response) => {
                if (!response.ok) throw new Error("Błąd w pobieraniu danych!");
                return response.json();
            })
            .then((data) => {
                setWeather(data);
                setLoading(false);
            })
            .catch((err) => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    return (
        <div className="flex justify-center flex-col gap-6 items-center min-h-screen bg-blue-100 dark:bg-gray-900 p-6 w-full max-w-7xl mx-auto">
            <header className="flex flex-col items-center">
                <a href="https://nubisoft.io/" target="_blank" rel="noopener noreferrer">
                    <img src={nubisoftLogo} alt="Nubisoft logo" className="w-32" />
                </a>
                <h1 className="text-3xl font-bold mt-4 text-gray-900 dark:text-white">NubiWeather</h1>
            </header>

            <section className="w-full">
                <h2 className="text-xl font-semibold text-center mb-4 text-gray-800 dark:text-gray-200">
                    Prognoza pogody dla Hamburga (1 dzień)
                </h2>

                {error && <p className="text-red-500 text-center">{error}</p>}

                {loading ? (
                    <p className="text-center text-gray-600 dark:text-gray-400">Ładowanie danych pogodowych...</p>
                ) : weather ? (
                    <div className="grid grid-cols-1 gap-6">
                        <WeatherCard
                            key={weather.date}
                            date={weather.date}
                            description={weather.description}
                            icon={weather.icon}
                            temp={weather.temp}
                        />
                    </div>
                ) : (
                    <p className="text-center text-gray-600 dark:text-gray-400">Brak danych pogodowych.</p>
                )}
            </section>
        </div>
    );
}

export default App;
