package org.example;

import org.example.observer.CurrentConditionsDisplay;
import org.example.observer_inner.CurrentConditionsDisplayInner;
import org.example.observer_inner.WeatherDataInner;

public class WeatherStationInnerTest {
    public static void main(String[] args) throws InterruptedException {
        WeatherDataInner weatherData = new WeatherDataInner();
        CurrentConditionsDisplayInner currentConditionsDisplay = new CurrentConditionsDisplayInner(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        Thread.sleep(1000);
        weatherData.setMeasurements(82, 70, 29.2f);
        Thread.sleep(1000);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
