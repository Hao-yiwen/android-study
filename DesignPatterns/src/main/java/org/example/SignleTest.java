package org.example;

import org.example.single.ChocolateBoiler;

public class SignleTest {
    public static void main(String[] args) {
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();
        boiler.fill();
        boiler.boil();
        boiler.drain();
    }
}
