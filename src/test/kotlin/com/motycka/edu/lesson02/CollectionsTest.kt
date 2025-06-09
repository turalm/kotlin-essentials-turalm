package com.motycka.edu.lesson02

import com.motycka.edu.captureStdout
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class CollectionsTest : StringSpec({

    "coffeeMenu should contain all coffee types" {
        coffeeMenu.size shouldBe 8
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

    "should print menu items" {
        captureStdout {
            collections()
        } shouldContain  """
            Welcome to the Coffee Shop! Here is our menu:
            Espresso
            Double Espresso
            Cappuccino
            Latte
            Macchiato
            Mocha
            Flat White
            Americano
        """.trimIndent()
    }

    "should print placed orders" {
        captureStdout {
            collections()
        } shouldContain  """
            Orders placed:
            Order ID: 1, Items: [Espresso, Cappuccino, Cappuccino, Americano]
            Order ID: 2, Items: [Espresso, Double Espresso, Flat White]
        """.trimIndent()
    }

})

