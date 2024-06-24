package org.example;

import org.example.observer.CurrentConditionsDisplay;
import org.example.observer.WeatherData;

public class WeatherStationTest {
    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        Thread.sleep(1000);
        weatherData.setMeasurements(82, 70, 29.2f);
        Thread.sleep(1000);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
