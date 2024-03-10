package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Place

object PlaceDataProvider {
    val defaultPlace = listOf<Place>(
        Place(
            1L,
            1L,
            R.string.cong_cong_na_nian,
            R.string.cong_cong_na_nian_location,
            R.drawable.congcongnanian,
            20
        ),
        Place(
            2L,
            1L,
            R.string.bu_wang_chu_xin,
            R.string.bu_wang_chu_xin_location,
            R.drawable.buwangchuxin,
            30
        ),
        Place(3L, 3L, R.string.zhi_yi, R.string.zhi_yi_location, R.drawable.zhiyi, 40),
        Place(
            4L,
            2L,
            R.string.flower_shop,
            R.string.flower_shop_location,
            R.drawable.xianhuakuaimai,
            50
        ),
    )
}