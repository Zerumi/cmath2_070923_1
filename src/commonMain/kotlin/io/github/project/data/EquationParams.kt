package io.github.project.data

import kotlinx.serialization.Serializable

@Serializable
data class EquationParams(
    val equation : String,
    val a : Double,
    val b : Double,
    val epsilon : Double,
    val solvingMethod: SolvingMethod,
)