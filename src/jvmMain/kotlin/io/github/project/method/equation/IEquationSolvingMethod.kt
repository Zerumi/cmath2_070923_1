package io.github.project.method.equation

import io.github.project.data.equation.EquationParams
import io.github.project.data.equation.EquationResult

interface IEquationSolvingMethod {
    fun solveEquation(equationParams: EquationParams): EquationResult
}