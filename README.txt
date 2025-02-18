# NubiWeather - Pogoda dla Hamburga i Gliwic

## Opis aplikacji

Aplikacja wyświetla aktualną pogodę dla dwóch miast: Hamburg i Gliwice.
Dane pogodowe są pobierane z zewnętrznego API pogodowego przy użyciu REST API.


### Technologie użyte w aplikacji:

- **Frontend**: React.js, Tailwind CSS
- **Backend**: Spring Boot
- **Komunikacja**: REST API
- **Zarządzanie zależnościami**: pnpm (frontend), Gradle (backend)


## Jak uruchomić aplikację?

### 1. Uruchomienie backendu (Spring Boot)
1. Upewnij się, że masz zainstalowaną **Javę 17+** oraz **Gradle**.
2. Przejdź do katalogu backendu.
3. Zbuduj i uruchom aplikację Spring Boot.
4. Backend będzie dostępny pod adresem: `http://localhost:8080`

### 2. Uruchomienie frontend (React.js)
1. Upewnij się, że masz zainstalowane **Node.js**.
2. Przejdź do katalogu frontend:
3. Zainstaluj zależności komendą "pnpm install" lub "npm install -g pnpm".
4. Uruchom aplikację React.js komendą "pnpm run dev".
5. Frontend będzie dostępny pod adresem: `http://localhost:5173`


## Jak działa aplikacja?

1. Po wejściu na stronę użytkownik widzi aktualną pogodę dla Hamburga.
2. Użytkownik ma do dyspozycji dwa przyciski: jeden umożliwia wyświetlenie prognozy na 7 dni, a drugi pozwala zmienić miasto na Gliwice.
3. Po kliknięciu przycisku może wyświetlić pogodę dla Hamburga na 7 dni.
4. Po kliknięciu przycisku zmiany miasta aplikacja pobiera i wyświetla aktualne dane pogodowe dla Gliwic.
5. Po kliknięciu przycisku prognozy 7-dniowej aplikacja pobiera szczegółową prognozę dla wybranego miasta.
6. Frontend wysyła żądania do backendu, który pobiera dane pogodowe z zewnętrznego API.
7. Backend zwraca odpowiedź w formacie JSON.
8. Frontend odbiera dane i wyświetla aktualną temperaturę, datę oraz warunki pogodowe (w formie tekstu i ikony).


## Zabezpieczenie przed nadmierną liczbą zapytań API

Aby zapobiec nadmiernemu obciążeniu aplikacji i zewnętrznego API pogodowego, 
w aplikacji zastosowano mechanizm rate limiting przy użyciu biblioteki Bucket4j. 
Mechanizm ten zarządza ruchem API, przydzielając 50 zapytań na minutę.


## Struktura komunikacji frontend-backend

- **Frontend**: Wysyła żądanie `GET` do endpointu backendu:
  GET /api/weather/hamburg
  GET /api/weather/gliwice
  lub
  GET /api/weather/hamburg/weekly
  GET /api/weather/gliwice/weekly

- **Backend**: Pobiera dane z API pogodowego, przetwarza je i zwraca w odpowiednim formacie JSON.
- **Przykładowa odpowiedź JSON z backendu**:
{
  "date": "Today",
  "description": "Sunny",
  "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
  "temp": 2.1
}


## Podsumowanie

Aplikacja umożliwia szybkie sprawdzenie pogody dla Hamburga i Gliwic w przejrzysty sposób.
Dzięki zastosowaniu **React.js**, **Spring Boot** oraz **REST API**, aplikacja jest wydajna i łatwa w rozbudowie.
