package com.motycka.edu.lesson03.model

/*
 This file contains interfaces for the shape hierarchy.
 Students should implement classes that fulfill these interfaces.
 */

/*
 Quadrilateral interface represents a four-sided 2D shape.

 It should have:
    - properties for width and length
    - a method to convert to a 3D shape with the given depth
 */
interface Quadrilateral {
    val width: Double
    val length: Double
    fun to3D(depth: Double): Shape3D
}

/*
 Ellipse interface represents an elliptical 2D shape.

 It should have:
    - a property for radius
    - a method to convert to a 3D shape
 */
interface Ellipse {
    val radius: Double
    fun to3D(): Shape3D
}

/*
 Shape2D interface represents any two-dimensional shape.

 It should have:
    - a method to calculate the area
    - a method to calculate the perimeter
 */
interface Shape2D {
    fun area(): Double
    fun perimeter(): Double
}

/*
 Shape3D interface represents any three-dimensional shape.

 It should have:
    - a method to calculate the volume
    - a method to calculate the surface area
 */
interface Shape3D {
    fun volume(): Double
    fun surfaceArea(): Double
}
