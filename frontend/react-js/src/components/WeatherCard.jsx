// eslint-disable-next-line no-unused-vars
import React from "react";

// eslint-disable-next-line react/prop-types
const WeatherCard = ({ date, description, icon, temp }) => {
    return (
        <div className="bg-white p-4 rounded-2xl shadow-md flex flex-col items-center">
            <p className="text-lg font-bold">{date}</p>
            <p className="text-gray-600">{description}</p>
            <img src={icon} alt={description} className="w-16 h-16"/>
            <p className="text-xl font-semibold">{temp}°C</p>
        </div>
    );
};

export default WeatherCard;
