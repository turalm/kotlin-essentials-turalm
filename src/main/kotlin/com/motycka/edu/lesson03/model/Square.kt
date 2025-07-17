package com.motycka.edu.lesson03.model

interface Shape2D {
    fun area(): Double
    fun perimeter(): Double
}

interface Quadrilateral {
    fun numberOfSides(): Int
    fun sideLengths(): List<Double>
}

open class Rectangle(
    protected val width: Double,
    protected val length: Double
) : Shape2D, Quadrilateral {

    override fun area(): Double = width * length

    override fun perimeter(): Double = 2 * (width + length)

    override fun numberOfSides(): Int = 4

    override fun sideLengths(): List<Double> = listOf(width, length, width, length)
}

class Cuboid(
    private val width: Double,
    private val height: Double,
    private val depth: Double
) : Shape3D {

    override fun surfaceArea(): Double = 2 * (width * height + height * depth + width * depth)

    override fun volume(): Double = width * height * depth
}

class Square(private val sideLength: Double) : Rectangle(sideLength, sideLength) {

    override fun area(): Double {
        return sideLength * sideLength
    }

    override fun perimeter(): Double {
        return 4 * sideLength
    }

    override fun numberOfSides(): Int {
        return 4
    }

    override fun sideLengths(): List<Double> {
        return List(4) { sideLength }
    }

    fun toCuboid(): Cuboid {
        return Cuboid(sideLength, sideLength, sideLength)
    }
}


fun main() {
    val square = Square(5.0)
    println("Area: ${square.area()}")
    println("Perimeter: ${square.perimeter()}")
    println("Sides: ${square.sideLengths()}")

    val cuboid = square.toCuboid()
    println("Cuboid Surface Area: ${cuboid.surfaceArea()}")
    println("Cuboid Volume: ${cuboid.volume()}")
}