package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Food

object FoodDataProvider {
    val defaultFood = listOf<Food>(
        Food(1L, R.string.coffee, R.drawable.coffee, R.string.coffee_describe),
        Food(2L, R.string.flower, R.drawable.flower, R.string.flower_describe),
        Food(3L, R.string.ice_cream, R.drawable.ice_cream, R.string.ice_cream_describe),
    )
}