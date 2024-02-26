package com.example.tiptime.test

class Quiz : ProgressPaintable {
    val qs1 = Question<String>("What is the capital of France?", "Paris", Difficulty.Hard)
    val qs2 = Question<Int>("What is 7 + 9?", 16, Difficulty.Easy)
    val qs3 = Question<Boolean>("Is the earth round?", true, Difficulty.Medium)

    companion object StudentProgress {
        var total: Int = 10
        var answer: Int = 3
    }

    override val progressText: String
        get() = "$answer of $total answered."

    override fun printProgressBar() {
        repeat(Quiz.answer) { print("▓") }
        repeat(Quiz.total - Quiz.answer) {
            print("▒")
        }
        println()
        println(progressText)
    }

    fun printQuiz() {
        qs1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        qs2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
        qs3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
    }
}

//val Quiz.StudentProgress.progressText: String


//fun Quiz.StudentProgress.printProgressBar() {
//
//}