package io.github.project.data.system

data class EquationSystemParams(
    val equations: List<String>,
    val startX: Double,
    val startY: Double,
    val epsilon : Double,
    val equationSystemSolvingMethod: EquationSystemSolvingMethod,
)
