package io.github.project.method

import io.github.project.EquationService
import io.github.project.data.EquationParams
import io.github.project.data.EquationResult
import io.github.project.data.SolvingMethod
import io.github.project.exception.LowEfficiencyMethodException
import io.github.project.util.FunctionUtil
import kotlin.math.abs

class SimpleIterationMethod : ISolvingMethod {

    private lateinit var equation: String

    fun calculateDer(x: Double): Double {
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
            SolvingMethod.SIMPLE_ITERATION_METHOD,
            xNext,
            EquationService.calculateFunction(equation, xNext),
            iterations
        )
    }
}