package com.motycka.edu

// Helper functions for reflection
fun getClass(canonicalName: String): Class<*> {
    return Class.forName(canonicalName)
}

fun Any.getPropertyValue(propertyName: String): Any? {
    // For data class properties, try to access them directly first
    val field = try {
        this.javaClass.getDeclaredField(propertyName)
    } catch (e: NoSuchFieldException) {
        null
    }

    if (field != null) {
        field.isAccessible = true
        return field.get(this)
    }

    // If direct field access fails, try getter method
    val method = try {
        this.javaClass.getDeclaredMethod("get${propertyName.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}")
    } catch (e: NoSuchMethodException) {
        null
    }

    return method?.invoke(this)
}

fun Any.callMethod(methodName: String, vararg args: Any?): Any? {
    // For setFinalGrade specifically, we know it takes an Int parameter
    if (methodName == "setFinalGrade" && args.size == 1 && args[0] is Number) {
        val methods = this.javaClass.declaredMethods
        val setFinalGradeMethod = methods.find { it.name == "setFinalGrade" && it.parameterCount == 1 }

        if (setFinalGradeMethod != null) {
            setFinalGradeMethod.isAccessible = true
            // Convert the argument to the expected type (Int)
            val convertedArg = (args[0] as Number).toInt()
            return setFinalGradeMethod.invoke(this, convertedArg)
        }
    }

    // For other methods, try to find the method with the exact parameter types
    val method = try {
        when {
            args.isEmpty() -> this.javaClass.getDeclaredMethod(methodName)
            args.size == 1 -> {
                if (args[0] == null) {
                    // If arg is null, try to find any method with the right name and 1 parameter
                    this.javaClass.declaredMethods.firstOrNull {
                        it.name == methodName && it.parameterCount == 1
                    } ?: throw NoSuchMethodException("No method found: $methodName with 1 parameter")
                } else {
                    // Try all methods with the right name and parameter count
                    val methods = this.javaClass.declaredMethods.filter {
                        it.name == methodName && it.parameterCount == 1
                    }

                    if (methods.isEmpty()) {
                        throw NoSuchMethodException("No method found: $methodName with 1 parameter")
                    }

                    // Try to find a method that can accept the argument type
                    val argType = args[0]!!.javaClass

                    methods.firstOrNull { method ->
                        val paramType = method.parameterTypes[0]

                        // Handle primitive types and their wrappers
                        when {
                            paramType == Double::class.java && (argType == Double::class.java || argType == java.lang.Double::class.java) -> true
                            paramType == Int::class.java && (argType == Int::class.java || argType == java.lang.Integer::class.java) -> true
                            paramType == Float::class.java && (argType == Float::class.java || argType == java.lang.Float::class.java) -> true
                            paramType == Long::class.java && (argType == Long::class.java || argType == java.lang.Long::class.java) -> true
                            paramType == Boolean::class.java && (argType == Boolean::class.java || argType == java.lang.Boolean::class.java) -> true
                            paramType == Char::class.java && (argType == Char::class.java || argType == java.lang.Character::class.java) -> true
                            paramType == Byte::class.java && (argType == Byte::class.java || argType == java.lang.Byte::class.java) -> true
                            paramType == Short::class.java && (argType == Short::class.java || argType == java.lang.Short::class.java) -> true
                            paramType.isAssignableFrom(argType) -> true
                            else -> false
                        }
                    } ?: methods.firstOrNull() ?: throw NoSuchMethodException("No suitable method found: $methodName")
                }
            }
            else -> {
                // Handle methods with multiple arguments
                val methods = this.javaClass.declaredMethods.filter {
                    it.name == methodName && it.parameterCount == args.size
                }

                if (methods.isEmpty()) {
                    throw NoSuchMethodException("No method found: $methodName with ${args.size} parameters")
                }

                // Try to find a method that can accept the argument types
                methods.firstOrNull { method ->
                    val parameterTypes = method.parameterTypes
                    args.indices.all { i ->
                        if (args[i] == null) {
                            true
                        } else {
                            val argType = args[i]!!.javaClass
                            val paramType = parameterTypes[i]

                            // Handle primitive types and their wrappers
                            when {
                                paramType == Double::class.java && (argType == Double::class.java || argType == java.lang.Double::class.java) -> true
                                paramType == Int::class.java && (argType == Int::class.java || argType == java.lang.Integer::class.java) -> true
                                paramType == Float::class.java && (argType == Float::class.java || argType == java.lang.Float::class.java) -> true
                                paramType == Long::class.java && (argType == Long::class.java || argType == java.lang.Long::class.java) -> true
                                paramType == Boolean::class.java && (argType == Boolean::class.java || argType == java.lang.Boolean::class.java) -> true
                                paramType == Char::class.java && (argType == Char::class.java || argType == java.lang.Character::class.java) -> true
                                paramType == Byte::class.java && (argType == Byte::class.java || argType == java.lang.Byte::class.java) -> true
                                paramType == Short::class.java && (argType == Short::class.java || argType == java.lang.Short::class.java) -> true
                                paramType.isAssignableFrom(argType) -> true
                                else -> false
                            }
                        }
                    }
                } ?: methods.firstOrNull() ?: throw NoSuchMethodException("No suitable method found: $methodName")
            }
        }
    } catch (e: NoSuchMethodException) {
        println("[DEBUG_LOG] Method not found: $methodName with args: ${args.joinToString()}")
        throw e
    }

    method.isAccessible = true
    return method.invoke(this, *args)
}
