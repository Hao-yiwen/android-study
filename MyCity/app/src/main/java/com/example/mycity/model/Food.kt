package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Food(
    val id: Long,
    @StringRes val name: Int,
    @DrawableRes val image: Int,
    @StringRes val describe: Int
)