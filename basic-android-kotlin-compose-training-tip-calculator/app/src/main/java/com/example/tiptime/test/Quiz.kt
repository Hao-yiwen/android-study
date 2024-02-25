package com.example.tiptime.test

class Quiz {
    val qs1 = Question<String>("What is the capital of France?", "Paris", Difficulty.Hard)
    val qs2 = Question<Int>("What is 7 + 9?", 16, Difficulty.Easy)
    val qs3 = Question<Boolean>("Is the earth round?", true, Difficulty.Medium)

    companion object StudentProgress {
        var total: Int = 10
        var answer: Int = 3
    }
}

val Quiz.StudentProgress.progressText:String
    get() = "$answer of $total answered."