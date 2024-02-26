package com.example.tiptime.test

fun main(){
    val rocketNames = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")

    val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune");

    val solarSystem = rocketNames + gasPlanets;

    solarSystem[3] = "Little Earth"

//    solarSystem[8] = "Pluto"

//    println(solarSystem[0])
//    println(solarSystem[1])
//    println(solarSystem[2])
//    println(solarSystem[3])
//    println(solarSystem[4])
//    println(solarSystem[5])
//    println(solarSystem[6])
//    println(solarSystem[7])

    println(solarSystem.size)

    for(item in solarSystem){
        println(item)
    }
}