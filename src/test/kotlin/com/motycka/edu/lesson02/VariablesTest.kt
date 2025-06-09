package com.motycka.edu.lesson02

import com.motycka.edu.captureStdout
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class VariablesTest : StringSpec({
    "coffee price variables should have correct values" {
        espressoPrice shouldBe 2.5
        cappuccinoPrice shouldBe 3.0
    }

    "should calculate total price for no coffees" {
        captureStdout {
            variables(espressoQuantity = 0, cappuccinoQuantity = 0)
        } shouldBe "0.0"
    }

    "should calculate total price for one espresso" {
        captureStdout {
            variables(espressoQuantity = 1, cappuccinoQuantity = 0)
        } shouldBe "2.5"
    }

    "should calculate total price for one cappuccino" {
        captureStdout {
            variables(espressoQuantity = 0, cappuccinoQuantity = 1)
        } shouldBe "3.0"
    }

    "should calculate total price for multiple coffees" {
        captureStdout {
            variables(espressoQuantity = 2, cappuccinoQuantity = 1)
        } shouldBe "8.0"
    }
})
