package com.motycka.edu.lesson01

import com.motycka.edu.captureStdout
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldContain

class MainTest : StringSpec({

    "main function should print 'Hello, Kotlin!'" {
        captureStdout {
            main(emptyArray())
        } shouldContain "Hello, Kotlin!"
    }

    "main function should print program arguments" {
        val args = arrayOf("arg1", "arg2", "arg3")
        captureStdout {
            main(args)
        } shouldContain "Program arguments: arg1, arg2, arg3"
    }
})
