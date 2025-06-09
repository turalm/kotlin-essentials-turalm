package com.motycka.edu.lesson02

val coffeeOrders = mutableMapOf<Int, List<String>>()

fun main() {
    // You can write code here to try the functions
    processOrder(listOf(ESPRESSO, CAPPUCCINO, CAPPUCCINO, AMERICANO), 20.0)
    processOrder(listOf(ESPRESSO, FLAT_WHITE, AMERICANO), 10.0)
    // processOrder(listOf(ESPRESSO, ESPRESSO, DOUBLE_ESPRESSO), 5.0) // will fail due to insufficient payment
}

/* Implement the functions below */

fun processOrder(items: List<String>, payment: Double): Double {
    val orderId = TODO("call placerOrder(items)")
    val totalToPay = TODO("call payOrder(orderId)")

    val change = TODO("calculate change by subtracting totalToPay from payment")

    // TODO call completeOrder(orderId)

    return change
}

// TODO Implement placerOrder(items: List<String>): Int

// TODO Implement payOrder(orderId: Int): Double

// TODO Implement completeOrder(orderId: Int)

