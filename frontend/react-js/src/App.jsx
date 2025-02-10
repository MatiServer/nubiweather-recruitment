import { useEffect, useState } from "react";
import nubisoftLogo from "./assets/nubisoft.svg";
import WeatherCard from "./components/WeatherCard";

function App() {
    const [weather, setWeather] = useState([]);
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
        <div className="flex justify-center flex-col gap-6 items-center min-h-screen bg-blue-100 p-6">
            <header className="flex flex-col items-center">
                <a href="https://nubisoft.io/" target="_blank" rel="noopener noreferrer">
                    <img src={nubisoftLogo} alt="Nubisoft logo" className="w-32" />
                </a>
                <h1 className="text-3xl font-bold mt-4">NubiWeather</h1>
            </header>

            <section className="w-full max-w-4xl">
                <h2 className="text-xl font-semibold text-center mb-4">
                    Prognoza pogody dla Hamburga (7 dni)
                </h2>

                {error && <p className="text-red-500 text-center">{error}</p>}

                {loading ? (
                    <p className="text-center text-gray-600">Ładowanie danych pogodowych...</p>
                ) : weather.length > 0 ? (
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                        {weather.map((day, index) => (
                            <WeatherCard
                                key={index}
                                date={day.date}
                                description={day.description}
                                icon={day.icon}
                                temp={day.temp}
                            />
                        ))}
                    </div>
                ) : (
                    <p className="text-center text-gray-600">Brak danych pogodowych.</p>
                )}
            </section>
        </div>
    );
}

export default App;
