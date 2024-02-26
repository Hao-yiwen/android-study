package com.example.tiptime.test

fun main(){
    val solarSystem = mutableSetOf<Any>("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystem.size)
    solarSystem.add("Pluto")
    println(solarSystem.contains("Pluto"))
    solarSystem.remove("Pluto")
}