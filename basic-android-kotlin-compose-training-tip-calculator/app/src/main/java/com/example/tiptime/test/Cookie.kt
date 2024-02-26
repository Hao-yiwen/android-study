package com.example.tiptime.test

class Cookie(val name: String, val softBaked: Boolean, val hasFilling: Boolean, val price: Double)

fun main() {
    val cookies = listOf(
        Cookie(
            name = "Chocolate Chip",
            softBaked = false,
            hasFilling = false,
            price = 1.69
        ),
        Cookie(
            name = "Banana Walnut",
            softBaked = true,
            hasFilling = false,
            price = 1.49
        ),
        Cookie(
            name = "Vanilla Creme",
            softBaked = false,
            hasFilling = true,
            price = 1.59
        ),
        Cookie(
            name = "Chocolate Peanut Butter",
            softBaked = false,
            hasFilling = true,
            price = 1.49
        ),
        Cookie(
            name = "Snickerdoodle",
            softBaked = true,
            hasFilling = false,
            price = 1.39
        ),
        Cookie(
            name = "Blueberry Tart",
            softBaked = true,
            hasFilling = true,
            price = 1.79
        ),
        Cookie(
            name = "Sugar and Sprinkles",
            softBaked = false,
            hasFilling = false,
            price = 1.39
        )
    )

    cookies.forEach({
        println("Menu item ${it.name}")
    })

    val fulMenu = cookies.map {
        "${it.name} - ${it.price}"
    }
    println(fulMenu)

//    val sorftBakedMenu = cookies.filter {
//        it.softBaked == true
//    }
//    println(sorftBakedMenu)

    val groupByMenu = cookies.groupBy {
        it.softBaked
    }
    val softBakedMenu = groupByMenu[true] ?: listOf()
    val crunchyMenu = groupByMenu[false] ?: listOf()
    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    println("Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }
//    需要指定类型
    val totalPrice = cookies.fold(0.0) { total, cookie ->
        total + cookie.price
    }
    println(totalPrice)
    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }
    alphabeticalMenu.forEach{
        println(it.name)
    }
}