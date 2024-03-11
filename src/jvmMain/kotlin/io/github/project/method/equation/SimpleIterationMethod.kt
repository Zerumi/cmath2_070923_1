package io.github.project.method.equation

import io.github.project.EquationService
import io.github.project.data.equation.EquationParams
import io.github.project.data.equation.EquationResult
import io.github.project.data.equation.EquationSolvingMethod
import io.github.project.exception.LowEfficiencyMethodException
import io.github.project.util.FunctionUtil
import kotlin.math.abs

class SimpleIterationMethod : IEquationSolvingMethod {

    private lateinit var equation: String

    private fun calculateDer(x: Double): Double {
        return EquationService.calculateDerivative(equation, x)
    }

    override fun solveEquation(equationParams: EquationParams): EquationResult {
        var iterations = 0u

        equation = equationParams.equation
        val a = equationParams.a
        val b = equationParams.b
        val epsilon = equationParams.epsilon

        val maximumOfDer = FunctionUtil.findMaximum(::calculateDer, a, b, epsilon)

        val lambda = -(1 / calculateDer(maximumOfDer))

        val phi = "x + $lambda*($equation)"

        val phiDerA = EquationService.calculateDerivative(phi, a)
        val phiDerB = EquationService.calculateDerivative(phi, b)

        if (abs(phiDerA) > 1 || abs(phiDerB) > 1) throw LowEfficiencyMethodException()

        var xLast = maximumOfDer
        var xNext = EquationService.calculateFunction(phi, xLast)
        while (abs(xNext - xLast) > epsilon) {
            if (iterations > 1000u) throw LowEfficiencyMethodException()
            xLast = xNext
            xNext = EquationService.calculateFunction(phi, xLast)
            iterations++
        }

        return EquationResult.ok(
            EquationSolvingMethod.SIMPLE_ITERATION_METHOD,
            xNext,
            EquationService.calculateFunction(equation, xNext),
            iterations
        )
    }
}