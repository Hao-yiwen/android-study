package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Long,
    val foodId: Long,
    @StringRes val name: Int,
    @StringRes val location: Int,
    @DrawableRes val image: Int,
    val price: Int
)