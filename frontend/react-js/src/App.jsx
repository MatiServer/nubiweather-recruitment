import { useEffect, useState } from "react";
import nubisoftLogo from "./assets/nubisoft.svg";
import WeatherCard from "./components/WeatherCard";
import WeatherList from "./components/WeatherList";

function App() {
    const [weather, setWeather] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);
    const [viewMode, setViewMode] = useState("single");
    const [city, setCity] = useState("hamburg");

    useEffect(() => {
        fetch(`http://localhost:8080/api/weather/${city}`)
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
    }, [city]);

    return (
        <div className="flex justify-center flex-col gap-6 items-center min-h-screen bg-blue-100 dark:bg-gray-900 p-6 w-screen max-w-full mx-auto">
            <header className="flex flex-col items-center">
                <a href="https://nubisoft.io/" target="_blank" rel="noopener noreferrer">
                    <img src={nubisoftLogo} alt="Nubisoft logo" className="w-32" />
                </a>
                <h1 className="text-3xl font-bold mt-4 text-gray-900 dark:text-white">NubiWeather</h1>
            </header>

            <section className="w-full">
                <h2 className="text-xl font-semibold text-center mb-4 text-gray-800 dark:text-gray-200">
                    Prognoza pogody dla {city === "hamburg" ? "Hamburga" : "Gliwic"} ({viewMode === "single" ? "1 dzień" : "7 dni"})
                </h2>

                {error && <p className="text-red-500 text-center">{error}</p>}

                {loading ? (
                    <p className="text-center text-gray-600 dark:text-gray-400">Ładowanie danych pogodowych...</p>
                ) : (
                    <>
                        {viewMode === "single" ? (
                            <div className="flex justify-center">
                                <WeatherCard
                                    key={weather.date}
                                    date={weather.date}
                                    description={weather.description}
                                    icon={weather.icon}
                                    temp={weather.temp}
                                />
                            </div>
                        ) : (
                            <WeatherList city={city} />
                            )}
                    </>
                )}

                <div className="w-full flex justify-center">
                    <button
                        onClick={() => setViewMode(viewMode === "single" ? "weekly" : "single")}
                        className="bg-blue-500 text-white font-semibold py-2 px-4 rounded-lg shadow-lg transition duration-300 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400 mt-4">
                        {viewMode === "single" ? "Pokaż 7-dniową prognozę" : "Pokaż 1-dniową prognozę"}
                    </button>
                </div>

                <div className="w-full flex justify-center mt-4">
                    <button
                        onClick={() => setCity(city === "hamburg" ? "gliwice" : "hamburg")}
                        className="bg-green-500 text-white font-semibold py-2 px-4 rounded-lg shadow-lg transition duration-300 hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-400 mt-4">
                        Zmień miasto na {city === "hamburg" ? "Gliwice" : "Hamburg"}
                    </button>
                </div>
            </section>
        </div>
    );
}

export default App;
