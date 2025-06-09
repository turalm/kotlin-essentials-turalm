package com.motycka.edu.lesson02

import com.motycka.edu.captureStdout
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldContain

class ConditionalsTest : StringSpec({

    "coffeeMenu should contain all coffee types" {
        coffeeMenu shouldHaveSize 8
        coffeeMenu shouldContainAll listOf(
            ESPRESSO,
            DOUBLE_ESPRESSO,
            CAPPUCCINO,
            LATTE,
            MACCHIATO,
            MOCHA,
            FLAT_WHITE,
            AMERICANO
        )
    }

    "orders should contain order 1" {
        captureStdout {
            conditionals()
        } shouldContain """
            Processing Order ID: 1
            Items: [Espresso, Cappuccino, Latte, Americano]
            You ordered 3 or more coffees, you get 1 for free!
            Total price for Order ID 1: 9.0
        """.trimIndent()
    }

    "orders should contain order 2" {
        captureStdout {
            conditionals()
        } shouldContain """
            Processing Order ID: 2
            Items: [Espresso, Double Espresso, Flat White]
            You ordered 3 or more coffees, you get 1 for free!
            Total price for Order ID 2: 6.199999999999999
        """.trimIndent()
    }

})
