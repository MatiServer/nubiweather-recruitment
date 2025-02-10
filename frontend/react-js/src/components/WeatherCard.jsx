// eslint-disable-next-line no-unused-vars
import React from "react";

// eslint-disable-next-line react/prop-types
const WeatherCard = ({ date, description, icon, temp }) => {
    return (
        <div className="max-w-xs sm:max-w-sm md:max-w-md lg:max-w-lg xl:max-w-xl p-4 bg-white rounded-lg shadow-lg dark:bg-gray-800">
            <h3 className="text-lg font-semibold text-gray-900 dark:text-white">{date}</h3>
            <p className="text-gray-700 dark:text-gray-300">{description}</p>
            <img
                src={`https:${icon}`}
                alt={description}
                className="w-16 h-16 mx-auto"
            />
            <p className="text-xl font-bold text-gray-900 dark:text-white">{temp}°C</p>
        </div>
    );
};

export default WeatherCard;

