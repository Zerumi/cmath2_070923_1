package io.github.project.data.system

import kotlinx.serialization.Serializable

@Serializable
data class EquationSystemParams(
    val equations: List<String>,
    val startApproximation: Map<String, Double>,
    val epsilon : Double,
    val equationSystemSolvingMethod: EquationSystemSolvingMethod,
)
