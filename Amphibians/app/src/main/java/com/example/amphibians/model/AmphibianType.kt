package com.example.amphibians.model

enum class AmphibianType {
    Toad,
    Frog,
    Salamander;

    companion object {
        fun fromType(type: String): AmphibianType? = when (type) {
            "Toad" -> Toad
            "Frog" -> Frog
            "Salamander" -> Salamander
            else -> Toad
        }
    }
}