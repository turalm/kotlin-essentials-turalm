package com.motycka.edu.lesson03.model

import kotlin.math.PI
import kotlin.math.pow

interface Shape3D {
    fun surfaceArea(): Double
    fun volume(): Double
}

class Sphere(private val radius: Double) : Shape3D {

    override fun surfaceArea(): Double {
        return 4 * PI * radius.pow(2)
    }

    override fun volume(): Double {
        return (4.0 / 3.0) * PI * radius.pow(3)
    }
}

fun main() {
    val sphere = Sphere(3.0)
    println("Surface Area: ${sphere.surfaceArea()}")
    println("Volume: ${sphere.volume()}")
}