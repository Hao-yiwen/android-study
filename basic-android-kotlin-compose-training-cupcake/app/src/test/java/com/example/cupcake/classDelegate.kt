package com.example.cupcake

interface Base {
    val number: Int
    fun print()
}

class classDelegate(val x: Int) : Base {
    override val number: Int = 10
    override fun print() {
        println("x = $x")
    }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = classDelegate(10)
    var x = Derived(b)
    x.print()
    println(x.number)
}