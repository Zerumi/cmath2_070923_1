package io.github.project.exception

open class CalculationException(val code: Int, override val message: String) : Exception(message)