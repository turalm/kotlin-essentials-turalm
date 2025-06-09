package com.motycka.edu.lesson03

import com.motycka.edu.callMethod
import com.motycka.edu.getClass
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.math.PI

class StaticPolymorphismTest : StringSpec({

    fun getAreaCalculator(): Any {
        return getClass("com.motycka.edu.lesson03.AreaCalculator").kotlin.objectInstance!!
    }

    "calculateRectangleArea with Double parameters should calculate correct area" {
        // given
        val a = 5.0
        val b = 10.0

        // when
        val area = getAreaCalculator().callMethod("calculateRectangleArea", a, b)

        // then
        area shouldBe 50.0
    }

    "calculateRectangleArea with Int parameters should calculate correct area" {
        // given
        val a = 5
        val b = 10

        // when
        val area = getAreaCalculator().callMethod("calculateRectangleArea", a, b)

        // then
        area shouldBe 50.0
    }

    "calculateCircleArea with Double parameter should calculate correct area" {
        // given
        val r = 5.0

        // when
        val area = getAreaCalculator().callMethod("calculateCircleArea", r)

        // then
        area shouldBe PI * r * r
    }

    "calculateCircleArea with Int parameter should calculate correct area" {
        // given
        val r = 5

        // when
        val area = getAreaCalculator().callMethod("calculateCircleArea", r)

        // then
        area shouldBe PI * r * r
    }

    "calculateTriangleArea with Double parameters should calculate correct area" {
        // given
        val a = 5.0
        val b = 10.0

        // when
        val area = getAreaCalculator().callMethod("calculateTriangleArea", a, b)

        // then
        area shouldBe 25.0
    }

    "calculateTriangleArea with Int parameters should calculate correct area" {
        // given
        val a = 5
        val b = 10

        // when
        val area = getAreaCalculator().callMethod("calculateTriangleArea", a, b)

        // then
        area shouldBe 25.0
    }
})
