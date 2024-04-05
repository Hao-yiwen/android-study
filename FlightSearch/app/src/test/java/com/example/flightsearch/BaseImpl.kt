package com.example.flightsearch

interface Base {
    val number: Int
    fun print()
}

class BaseImpl(val x: Int, override val number: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = BaseImpl(10, 20)
    // 将委托的对象传进来 然后复制该对象其他属性和方法
//    val b = classDelegate(10)
    var x = Derived(b)
    x.print()
}