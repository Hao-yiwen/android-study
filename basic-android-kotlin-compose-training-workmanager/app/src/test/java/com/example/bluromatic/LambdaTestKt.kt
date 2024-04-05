package com.example.bluromatic

import org.junit.Test

class LambdaTestKt {

    @Test
    fun lambdaTest() {
        val test = { x: Int, y: Int -> x + y }

        println(test(1, 2))
    }


    fun interface Mutiply {
        fun multiply(x: Int, y: Int): Int
    }

    @Test
    fun noLambdaTest() {
        val muptily = object : Mutiply {
            override fun multiply(x: Int, y: Int): Int {
                return x * y
            }
        }
        println(muptily.multiply(1, 2))
    }


}