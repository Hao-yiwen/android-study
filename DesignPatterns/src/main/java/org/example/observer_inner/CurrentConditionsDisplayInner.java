package org.example.observer_inner;

import org.example.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplayInner implements Observer, DisplayElement {
    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplayInner(Observable o) {
        this.observable = o;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherDataInner) {
            WeatherDataInner weatherDataInner = (WeatherDataInner) o;
            this.temperature = weatherDataInner.getTemperature();
            this.humidity = weatherDataInner.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}
