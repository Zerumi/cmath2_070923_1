package io.github.project.util

import io.github.project.EquationSystemService

class FunctionArgumentDerivative(
    private val function: String,
    private val variable: String,
) {
    fun calculateDerivative(variables: Map<String, Double>): Double {
        return EquationSystemService.calculateDerivativeByVariable(function, variable, variables)
    }
}