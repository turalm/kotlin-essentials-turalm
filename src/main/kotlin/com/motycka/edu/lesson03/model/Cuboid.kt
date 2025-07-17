package com.motycka.edu.lesson03.model

interface Shape3D {
    fun surfaceArea(): Double
    fun volume(): Double
}

class Cuboid(
    private val width: Double,
    private val height: Double,
    private val depth: Double
) : Shape3D {

    override fun surfaceArea(): Double {
        return 2 * (width * height + height * depth + width * depth)
    }

    override fun volume(): Double {
        return width * height * depth
    }
}

fun main() {
    val cuboid = Cuboid(width = 3.0, height = 4.0, depth = 5.0)
    println("Surface Area: ${cuboid.surfaceArea()}")
    println("Volume: ${cuboid.volume()}")
}