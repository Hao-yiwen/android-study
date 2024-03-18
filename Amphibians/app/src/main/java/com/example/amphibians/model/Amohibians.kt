package com.example.amphibians.model

import kotlinx.serialization.Serializable

@Serializable
data class Amohibians(
    val name: String,
    @Serializable(with = AmphibianTypeSerializer::class)
    val type: String,
    val description: String,
    val img_src: String
)