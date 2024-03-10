package io.github.project.method

import io.github.project.data.EquationParams
import io.github.project.data.EquationResult

interface ISolvingMethod {
    fun solveEquation(equationParams: EquationParams): EquationResult
}