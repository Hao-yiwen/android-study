package org.example.observer;

/**
 * @desc 自己实现observer
 */
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}
