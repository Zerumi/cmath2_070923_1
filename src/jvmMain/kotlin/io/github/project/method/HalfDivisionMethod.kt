package io.github.project.method

import io.github.project.EquationService
import io.github.project.data.EquationError
import io.github.project.data.EquationParams
import io.github.project.data.EquationResult
import io.github.project.data.SolvingMethod

class HalfDivisionMethod : ISolvingMethod {
    override fun solveEquation(equationParams: EquationParams): EquationResult {
        val equation = equationParams.equation
        val epsilon = equationParams.epsilon

        var iterations = 0u

        var a = equationParams.a
        var b = equationParams.b
        var fA = EquationService.calculateFunction(equation, a)

        while (b - a > epsilon / 2) {
            val x = (a + b) / 2
            val fX = EquationService.calculateFunction(equationParams.equation, x)

            if (fA * fX > 0) {
                a = x
                fA = EquationService.calculateFunction(equation, a)
            } else {
                b = x
            }

            iterations++
        }

        val xRes = (a + b) / 2
        val fXRes = EquationService.calculateFunction(equation, xRes)

        val result = EquationResult(
            true, EquationError.ok(), SolvingMethod.HALF_DIVISION_METHOD, xRes, fXRes, iterations
        )

        return result
    }
}