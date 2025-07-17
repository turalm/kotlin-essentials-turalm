package com.motycka.edu.lesson02

val coffeeOrders = mutableMapOf<Int, List<String>>()
var nextOrderId = 1

fun main() {
    // You can write code here to try the functions
    processOrder(listOf(ESPRESSO, CAPPUCCINO, CAPPUCCINO, AMERICANO), 20.0)
    processOrder(listOf(ESPRESSO, FLAT_WHITE, AMERICANO), 10.0)
    // processOrder(listOf(ESPRESSO, ESPRESSO, DOUBLE_ESPRESSO), 5.0) // will fail due to insufficient payment
}

/* Implement the functions below */

fun processOrder(items: List<String>, payment: Double): Double {
    val orderId = placeOrder(items)
    val totalToPay = payOrder(orderId)
    if (payment < totalToPay) {
        throw IllegalArgumentException("Insufficient payment! Total: $totalToPay, Payment: $payment")
    }
    val change = payment - totalToPay
    completeOrder(orderId)
    return String.format("%.2f", change).toDouble()  // rounding to 2 decimal places
}

fun placeOrder(items: List<String>): Int {
    val orderId = nextOrderId++
    coffeeOrders[orderId] = items
    println("Order placed: ID=$orderId, Items=$items")
    return orderId
}

fun payOrder(orderId: Int): Double {
    val items = coffeeOrders[orderId] ?: throw IllegalArgumentException("Order not found: $orderId")
    val prices = items.map { getPrice(it) }
    var total = prices.sum()

    if (items.size >= 3) {
        val discount = prices.minOrNull() ?: 0.0
        total -= discount
        println("Discount applied to Order $orderId: -$discount")
    }

    println("Total to pay for Order $orderId: %.2f".format(total))
    return total
}

fun completeOrder(orderId: Int) {
    coffeeOrders.remove(orderId)
    println("Order $orderId completed and removed from system.")
}

