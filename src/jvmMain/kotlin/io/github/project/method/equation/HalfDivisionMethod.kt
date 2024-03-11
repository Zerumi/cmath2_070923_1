package io.github.project.method.equation

import io.github.project.EquationService
import io.github.project.data.equation.EquationParams
import io.github.project.data.equation.EquationResult
import io.github.project.data.equation.EquationSolvingMethod

class HalfDivisionMethod : IEquationSolvingMethod {
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
        val yRes = EquationService.calculateFunction(equation, xRes)

        return EquationResult.ok(EquationSolvingMethod.HALF_DIVISION_METHOD, xRes, yRes, iterations)
    }
}