package com.example.cupcake

import kotlin.reflect.KProperty

class byTest {
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by Delegate()

    var name:String by Delegate()
}

fun main() {
    val example = Example()
    println(example.p) // 调用getValue()

    example.p = "New value" // 调用setValue()

    println(example.name)
}