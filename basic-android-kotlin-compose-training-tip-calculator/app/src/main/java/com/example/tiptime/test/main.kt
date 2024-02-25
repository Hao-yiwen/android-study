package com.example.tiptime.test

fun main(){
    val qs1 = Question<String>("What is the capital of France?", "Paris", Difficulty.Hard)
//    println(qs.answer.equals("Paris"))
    val qs2 = Question<Int>("What is 7 + 9?", 16, Difficulty.Easy)
//    println(qs2.answer.equals(16))
    val qs3 = Question<Boolean>("Is the earth round?", true, Difficulty.Medium)
//    println(qs3.answer.equals(true))
    println(qs1.toString())
    println("${Quiz.answer} of ${Quiz.total} answered.")
    println(Quiz.progressText)
}