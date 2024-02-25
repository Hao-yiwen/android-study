package com.example.tiptime.test

data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)