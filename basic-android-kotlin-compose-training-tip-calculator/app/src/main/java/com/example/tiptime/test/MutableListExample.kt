package com.example.tiptime.test

fun main(){
    val solarSystem = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    solarSystem.add("test")
    for (item in solarSystem){
//        println(item)
    }
    solarSystem.add(1,"test")
    for (item in solarSystem){
//        println(item)
    }
    solarSystem[0] = "hahahha"
//    for (item in solarSystem){
//        println(item)
//    }
    solarSystem.removeAt(0)
    solarSystem.remove("test")
    for (item in solarSystem){
        println(item)
    }
    println(solarSystem.contains("test"))
    println("Future Moon" in solarSystem)
}