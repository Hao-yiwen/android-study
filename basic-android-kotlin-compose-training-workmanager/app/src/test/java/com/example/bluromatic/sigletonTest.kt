package com.example.bluromatic

import org.junit.Test

class sigletonTest {
    object singleTon {
        var number = 0;

        fun increase() {
            number++
        }
    }

    @Test
    fun test1() {
        val number1 = singleTon.number
        println(number1)
        singleTon.increase()
        println(singleTon.number)

        val number2 = singleTon.number
        println(number2)
    }


    class Singleton1 private constructor() {
        var number = 0

        fun increase() {
            number++
        }

        companion object{
            /**
             * @description: 确保对所有线程的可见性
             */
            @Volatile
            private var instance: Singleton1? = null

            /**
             * @description: 双重校验锁式
             */
            fun getInstance(): Singleton1 = instance ?: synchronized(this) {
                instance ?: Singleton1().also { instance = it }
            }
        }
    }

    @Test
    fun test2(){
        val singleton1 = Singleton1.getInstance()

        println(singleton1.number)

        singleton1.increase()

        println(singleton1.number)

        singleton1.increase()

        val singleton2 = Singleton1.getInstance()

        println(singleton2.number)

        singleton2.increase()

        println(singleton1.number)
    }

    companion object{
        final val number = 1;

    }
}

fun main(){
    println(sigletonTest.number)
}