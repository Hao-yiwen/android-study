package com.example.javaviewtest.cpp;

public class MyCppWrapper {
    static {
        System.loadLibrary("MyCppProject1");
    }

    public native void printMessage();
}
