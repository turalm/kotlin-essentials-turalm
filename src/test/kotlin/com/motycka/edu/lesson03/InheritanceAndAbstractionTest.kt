package com.motycka.edu.lesson03

import com.motycka.edu.callMethod
import com.motycka.edu.getClass
import com.motycka.edu.getPropertyValue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.lang.reflect.Constructor
import java.lang.reflect.Modifier

class InheritanceAndAbstractionTest : FreeSpec({

    fun createCircle(radius: Double): Any {
        val circleClass = getClass("com.motycka.edu.lesson03.model.Circle")
        val constructor: Constructor<*> = circleClass.getDeclaredConstructor(Double::class.java)
        return constructor.newInstance(radius)
    }

    fun createRectangle(width: Double, length: Double): Any {
        val rectangleClass = getClass("com.motycka.edu.lesson03.model.Rectangle")
        val constructor: Constructor<*> = rectangleClass.getDeclaredConstructor(Double::class.java, Double::class.java)
        return constructor.newInstance(width, length)
    }

    fun createSquare(side: Double): Any {
        val squareClass = getClass("com.motycka.edu.lesson03.model.Square")
        val constructor: Constructor<*> = squareClass.getDeclaredConstructor(Double::class.java)
        return constructor.newInstance(side)
    }

    fun createSphere(radius: Double): Any {
        val sphereClass = getClass("com.motycka.edu.lesson03.model.Sphere")
        val constructor: Constructor<*> = sphereClass.getDeclaredConstructor(Double::class.java)
        return constructor.newInstance(radius)
    }

    fun createCuboid(width: Double, height: Double, depth: Double): Any {
        val cuboidClass = getClass("com.motycka.edu.lesson03.model.Cuboid")
        val constructor: Constructor<*> = cuboidClass.getDeclaredConstructor(Double::class.java, Double::class.java, Double::class.java)
        return constructor.newInstance(width, height, depth)
    }

    fun isInstanceOf(obj: Any, className: String): Boolean {
        val targetClass = getClass(className)
        return targetClass.isInstance(obj)
    }

    "Circle should implement Shape2D and Ellipse and have correct properties" {
        val circleClass = getClass("com.motycka.edu.lesson03.model.Circle")
        val shape2DClass = getClass("com.motycka.edu.lesson03.model.Shape2D")
        val ellipseClass = getClass("com.motycka.edu.lesson03.model.Ellipse")

        circleClass.interfaces.map { it.name }.toSet() shouldBe
            setOf(shape2DClass.name, ellipseClass.name)

        val circle = createCircle(5.0)
        circle.getPropertyValue("radius") shouldBe 5.0

        circle.callMethod("area") shouldBe Math.PI * 25.0
        circle.callMethod("perimeter") shouldBe 2 * Math.PI * 5.0

        val sphere = circle.callMethod("to3D")
        isInstanceOf(sphere!!, "com.motycka.edu.lesson03.model.Sphere") shouldBe true
    }

    "Rectangle should implement Shape2D and Quadrilateral and have correct properties" {
        val rectangleClass = getClass("com.motycka.edu.lesson03.model.Rectangle")
        val shape2DClass = getClass("com.motycka.edu.lesson03.model.Shape2D")
        val quadrilateralClass = getClass("com.motycka.edu.lesson03.model.Quadrilateral")

        rectangleClass.interfaces.map { it.name }.toSet() shouldBe
            setOf(shape2DClass.name, quadrilateralClass.name)

        rectangleClass.modifiers and Modifier.FINAL shouldBe 0

        val rectangle = createRectangle(4.0, 6.0)
        rectangle.getPropertyValue("width") shouldBe 4.0
        rectangle.getPropertyValue("length") shouldBe 6.0

        rectangle.callMethod("area") shouldBe 24.0
        rectangle.callMethod("perimeter") shouldBe 20.0

        val cuboid = rectangle.callMethod("to3D", 2.0)
        isInstanceOf(cuboid!!, "com.motycka.edu.lesson03.model.Cuboid") shouldBe true
        cuboid.getPropertyValue("width") shouldBe 4.0
        cuboid.getPropertyValue("height") shouldBe 6.0
        cuboid.getPropertyValue("depth") shouldBe 2.0
    }

    "Square should extend Rectangle and have correct methods" {
        val rectangleClass = getClass("com.motycka.edu.lesson03.model.Rectangle")
        val squareClass = getClass("com.motycka.edu.lesson03.model.Square")
        val isSubclass = rectangleClass.isAssignableFrom(squareClass)
        isSubclass shouldBe true

        val square = createSquare(5.0)
        square.getPropertyValue("side") shouldBe 5.0

//        square.callMethod("area") shouldBe 25.0
//        square.callMethod("perimeter") shouldBe 20.0

        val cuboid = square.callMethod("to3D")
        isInstanceOf(cuboid!!, "com.motycka.edu.lesson03.model.Cuboid") shouldBe true
        cuboid.getPropertyValue("width") shouldBe 5.0
        cuboid.getPropertyValue("height") shouldBe 5.0
        cuboid.getPropertyValue("depth") shouldBe 5.0
    }

    "Sphere should implement Shape3D and have correct methods" {
        val sphereClass = getClass("com.motycka.edu.lesson03.model.Sphere")
        val shape3DClass = getClass("com.motycka.edu.lesson03.model.Shape3D")

        sphereClass.interfaces.map { it.name }.toSet() shouldBe
            setOf(shape3DClass.name)

        val sphere = createSphere(3.0)

        sphere.callMethod("volume") shouldBe (4.0/3.0) * Math.PI * 27.0
        sphere.callMethod("surfaceArea") shouldBe 4 * Math.PI * 9.0
    }

    "Cuboid should implement Shape3D and have correct properties and methods" {
        val cuboidClass = getClass("com.motycka.edu.lesson03.model.Cuboid")
        val shape3DClass = getClass("com.motycka.edu.lesson03.model.Shape3D")

        cuboidClass.interfaces.map { it.name }.toSet() shouldBe
            setOf(shape3DClass.name)

        val cuboid = createCuboid(2.0, 3.0, 4.0)
        cuboid.getPropertyValue("width") shouldBe 2.0
        cuboid.getPropertyValue("height") shouldBe 3.0
        cuboid.getPropertyValue("depth") shouldBe 4.0

        cuboid.callMethod("volume") shouldBe 24.0
        cuboid.callMethod("surfaceArea") shouldBe 2 * (2*3 + 2*4 + 3*4)
    }
})
