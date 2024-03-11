package io.github.project.method.system.error

import io.github.project.EquationSystemService

fun calculateErrorVector(equations: List<String>, variables: Map<String, Double>): List<Double> {
    return equations.map { EquationSystemService.calculateFunctionWithMultipleArguments(it, variables) }
}