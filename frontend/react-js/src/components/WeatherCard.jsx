// eslint-disable-next-line no-unused-vars
import React from "react";
import { motion } from "framer-motion";

// eslint-disable-next-line react/prop-types
const WeatherCard = ({ date, description, icon, temp, delay }) => {
    return (
        <motion.div
            className="w-full max-w-md p-4 bg-white rounded-lg shadow-lg dark:bg-gray-800"
            initial={{opacity: 0, y: 20}}  // Startowe wartości (niewidoczny, lekko w dół)
            animate={{opacity: 1, y: 0}}  // Końcowe wartości (pełna widoczność)
            transition={{duration: 0.6, ease: "easeOut", delay: delay}} // Czas animacji
        >
            <h3 className="text-lg font-semibold text-gray-900 dark:text-white">{date}</h3>
            <p className="text-gray-700 dark:text-gray-300">{description}</p>
            <img
                src={`https:${icon}`}
                alt={description}
                className="w-16 h-16 mx-auto"
            />
            <p className="text-xl font-bold text-gray-900 dark:text-white">{temp}°C</p>
        </motion.div>
    );
};
export default WeatherCard;

