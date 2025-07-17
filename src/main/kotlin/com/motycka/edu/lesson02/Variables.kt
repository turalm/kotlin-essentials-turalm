package com.motycka.edu.lesson02

/*
Define String variables for two types of coffee: espresso and cappuccino.
 */
const val espresso = "Espresso"
const val cappuccino = "Cappuccino"

/*
Define Double variables for the prices of espresso and cappuccino.
 */
val espressoPrice = 2.5
val cappuccinoPrice = 3.0



fun variables(espressoQuantity: Int, cappuccinoQuantity: Int) {
    val totalPrice: Double = (espressoQuantity * espressoPrice) + (cappuccinoQuantity * cappuccinoPrice)
    println("Total price: %.2f".format(totalPrice))
}

fun main() {
    print("Enter espresso quantity: ")
    val espressoQuantity = readln().toIntOrNull() ?: 0

    print("Enter cappuccino quantity: ")
    val cappuccinoQuantity = readln().toIntOrNull() ?: 0

    variables(
        espressoQuantity = espressoQuantity,
        cappuccinoQuantity = cappuccinoQuantity
    )
}