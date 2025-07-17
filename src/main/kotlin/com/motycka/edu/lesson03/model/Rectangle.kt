package com.motycka.edu.lesson03.model

interface Shape2D {
    fun area(): Double
    fun perimeter(): Double
}

interface Quadrilateral {
    fun numberOfSides(): Int
    fun sideLengths(): List<Double>
}

class Rectangle(
    private val width: Double,
    private val length: Double
) : Shape2D, Quadrilateral {

    override fun area(): Double {
        return width * length
    }

    override fun perimeter(): Double {
        return 2 * (width + length)
    }

    override fun numberOfSides(): Int {
        return 4
    }

    override fun sideLengths(): List<Double> {
        return listOf(width, length, width, length)
    }
}


fun main() {
    val rect = Rectangle(width = 4.0, length = 6.0)
    println("Area: ${rect.area()}")
    println("Perimeter: ${rect.perimeter()}")
    println("Number of sides: ${rect.numberOfSides()}")
    println("Side lengths: ${rect.sideLengths()}")
}