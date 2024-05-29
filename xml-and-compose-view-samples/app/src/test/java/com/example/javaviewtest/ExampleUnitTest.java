package com.example.javaviewtest;

import org.junit.Test;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.IntUnaryOperator;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testLambda() {
        final int multiplier = 3;
        IntUnaryOperator intUnaryOperator = i -> i * multiplier;

        System.out.println(intUnaryOperator.applyAsInt(10));
    }


    interface MyInterface {
        int myMethod(int number);
    }

    @Test
    public void testLambda2() {
        final int multiplier = 3;
        MyInterface test = new MyInterface() {
            @Override
            public int myMethod(int number) {
                return number * multiplier;
            }
        };

        System.out.println(test.myMethod(10));
    }

    @Test
    public void testLambda3() {
        String url = "/rn_test?moduleName=test&params";
        try {
            URI uri = new URI(url);
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println(uri.getQuery());
            System.out.println(uri.getFragment());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}