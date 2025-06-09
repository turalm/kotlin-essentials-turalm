package com.motycka.edu.lesson03

import com.motycka.edu.callMethod
import com.motycka.edu.getClass
import com.motycka.edu.getPropertyValue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.reflect.Constructor
import java.time.LocalDate

class EncapsulationTest : StringSpec({

    fun createAssignment(dueDate: LocalDate, assignee: String): Any {
        val constructor: Constructor<*> = getClass("com.motycka.edu.lesson03.Assignment").getDeclaredConstructor(LocalDate::class.java, String::class.java)
        return constructor.newInstance(dueDate, assignee)
    }

    "Assignment constructor should initialize properties correctly" {
        // given
        val dueDate = LocalDate.of(2023, 12, 31)
        val assignee = "John Doe"

        // when
        val assignment = createAssignment(dueDate, assignee)

        // then
        assignment.getPropertyValue("dueDate") shouldBe dueDate
        assignment.getPropertyValue("assignee") shouldBe assignee
        assignment.callMethod("getFinalGrade") shouldBe null
    }

    "getFinalGrade should return null when grade is not set" {
        // given
        val assignment = createAssignment(LocalDate.now(), "Jane Smith")

        // when
        val grade = assignment.callMethod("getFinalGrade")

        // then
        grade shouldBe null
    }

    "setFinalGrade should set a valid grade" {
        // given
        val assignment = createAssignment(LocalDate.now(), "Alice Johnson")
        val validGrade = 85

        // when
        assignment.callMethod("setFinalGrade", validGrade)

        // then
        assignment.callMethod("getFinalGrade") shouldBe validGrade
    }

    "setFinalGrade should accept minimum valid grade (0)" {
        // given
        val assignment = createAssignment(LocalDate.now(), "Bob Brown")
        val minGrade = 0

        // when
        assignment.callMethod("setFinalGrade", minGrade)

        // then
        assignment.callMethod("getFinalGrade") shouldBe minGrade
    }

    "setFinalGrade should accept maximum valid grade (100)" {
        // given
        val assignment = createAssignment(LocalDate.now(), "Charlie Davis")
        val maxGrade = 100

        // when
        assignment.callMethod("setFinalGrade", maxGrade)

        // then
        assignment.callMethod("getFinalGrade") shouldBe maxGrade
    }

    "setFinalGrade should throw exception for grade below 0" {
        // given
        val assignment = createAssignment(LocalDate.now(), "David Wilson")
        val invalidGrade = -1

        // when/then
        try {
            assignment.callMethod("setFinalGrade", invalidGrade)
            throw AssertionError("Expected IllegalArgumentException was not thrown")
        } catch (e: java.lang.reflect.InvocationTargetException) {
            // Extract the original exception
            val cause = e.cause
            if (cause is IllegalArgumentException) {
                cause.message shouldBe "Final grade must be between 0 and 100"
            } else {
                throw AssertionError("Expected IllegalArgumentException but got ${cause?.javaClass?.name}")
            }
        }
    }

    "setFinalGrade should throw exception for grade above 100" {
        // given
        val assignment = createAssignment(LocalDate.now(), "Eva Martinez")
        val invalidGrade = 101

        // when/then
        try {
            assignment.callMethod("setFinalGrade", invalidGrade)
            throw AssertionError("Expected IllegalArgumentException was not thrown")
        } catch (e: java.lang.reflect.InvocationTargetException) {
            // Extract the original exception
            val cause = e.cause
            if (cause is IllegalArgumentException) {
                cause.message shouldBe "Final grade must be between 0 and 100"
            } else {
                throw AssertionError("Expected IllegalArgumentException but got ${cause?.javaClass?.name}")
            }
        }
    }
})
