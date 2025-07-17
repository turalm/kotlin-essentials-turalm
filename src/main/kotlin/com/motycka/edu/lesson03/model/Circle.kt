package com.motycka.edu.lesson03.model

import kotlin.math.PI

interface Shape2D {
    fun area(): Double
    fun perimeter(): Double
}

interface Ellipse {
    fun majorAxis(): Double
    fun minorAxis(): Double
}

class Circle(private val radius: Double) : Shape2D, Ellipse {

    override fun area(): Double {
        return PI * radius * radius
    }

    override fun perimeter(): Double {
        return 2 * PI * radius
    }

    override fun majorAxis(): Double {
        return 2 * radius
    }

    override fun minorAxis(): Double {
        return 2 * radius
    }
}

fun main() {
    val c = Circle(5.0)
    println("Area: ${c.area()}")
    println("Perimeter: ${c.perimeter()}")
    println("Major Axis: ${c.majorAxis()}")
    println("Minor Axis: ${c.minorAxis()}")
}