export async function fetchWeather() {
    const response = await fetch("http://localhost:8080/api/weather/hamburg");
    return response.json();
}
