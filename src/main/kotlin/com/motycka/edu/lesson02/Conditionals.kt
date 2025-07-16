package com.motycka.edu.lesson02


const val ESPRESSO_PRICE = 2.5
const val DOUBLE_ESPRESSO_PRICE = 3.0
const val CAPPUCCINO_PRICE = 3.0
const val LATTE_PRICE = 3.5
const val AMERICANO_PRICE = 2.0
const val FLAT_WHITE_PRICE = 3.2

/*
    1. Get price bas on item names
    2. Get the lowest price from a list of prices
    3. Calculate a total price for a list of items
    4. Calculate a discount if 3 or more items are ordered (the cheapest item is free)
    5. Print the total price with a discount applied for each order
 */

fun getPrice(item: String): Double {
    return when (item) {
        "Espresso" -> ESPRESSO_PRICE
        "Double Espresso" -> DOUBLE_ESPRESSO_PRICE
        "Cappuccino" -> CAPPUCCINO_PRICE
        "Latte" -> LATTE_PRICE
        "Americano" -> AMERICANO_PRICE
        "Flat White" -> FLAT_WHITE_PRICE
        else -> 0.0
    }
}

fun conditionals() {
    val coffeeOrders = mapOf(
        1 to listOf(ESPRESSO, CAPPUCCINO, LATTE, AMERICANO),
        2 to listOf(ESPRESSO, DOUBLE_ESPRESSO, FLAT_WHITE)
    )

    coffeeOrders.forEach { orderId, items ->

        println("Processing Order ID: $orderId")
        println("Items: $items")

        val prices = items.map { getPrice(it) }
        var total = prices.sum()
        if (items.size >= 3) {
            val cheapest = prices.minOrNull() ?: 0.0
            total -= cheapest
            println("Discount applied: -$cheapest (cheapest item free)")
        }


        println("Total price for Order ID $orderId: %.2f".format(total))
    }
}

fun main() {
    conditionals()
}
