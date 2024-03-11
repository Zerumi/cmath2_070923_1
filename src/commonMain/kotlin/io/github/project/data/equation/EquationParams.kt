package io.github.project.data.equation

import kotlinx.serialization.Serializable

@Serializable
data class EquationParams(
    val equation: String,
    val a: Double,
    val b: Double,
    val epsilon: Double,
    val equationSolvingMethod: EquationSolvingMethod,
)
