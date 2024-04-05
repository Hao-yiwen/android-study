package com.example.bluromatic;

import org.junit.Test;

import java.util.function.IntBinaryOperator;

public class LambdaTest {

    @Test
    public void test() {
        IntBinaryOperator intUnaryOperator = (x, y) -> {
            return x * y;
        };

        System.out.println(intUnaryOperator.applyAsInt(10, 20));
    }

    class MutilyInterface{
        int mutily(int x, int y){
            return x + y;
        }
    }

    @Test
    public void noLambda(){

        MutilyInterface mutilyInterface = new MutilyInterface() {
            @Override
            public int mutily(int x, int y) {
                return x * y;
            }
        };

        final int number = 0;

        System.out.println(mutilyInterface.mutily(10, 20));
    }




}
