package com.example.flightsearch.ui.screen

import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.FavoriteContainer
import java.util.UUID

data class HomeScreenUiState(
    val searchStr: String = "",
    val searchResult: List<Airport> = emptyList(),
    val allAirport: List<Airport> = emptyList(),
    val selectAirport: Airport? = null,
    val isShowSearchList: Boolean = false,
    val favoriteAirports: List<FavoriteContainer> = emptyList()
) {
    val searchAirportMap: List<FavoriteContainer>
        get() {
            /**
             * @description 将搜索到的结果和所有机场匹配，返回一个map
             */
            val res = mutableListOf<FavoriteContainer>()
            if (selectAirport == null || allAirport.isEmpty()) return res

            allAirport.forEach { airport ->
                res.add(
                    FavoriteContainer(
                        id = UUID.randomUUID().toString().hashCode(),
                        departureCode = selectAirport.iataCode,
                        departureName = selectAirport.name,
                        destinationCode = airport.iataCode,
                        destinationName = airport.name,
                        isFavorite = favoriteAirports.any { it.destinationCode == airport.iataCode && it.departureCode == selectAirport.iataCode }
                    )
                )
            }

            return res
        }

}