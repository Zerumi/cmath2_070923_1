package io.github.project.method.equation

import io.github.project.EquationService
import io.github.project.data.equation.EquationParams
import io.github.project.data.equation.EquationResult
import io.github.project.data.equation.EquationSolvingMethod
import io.github.project.exception.LowEfficiencyMethodException
import kotlin.math.abs

class NewtonMethod : IEquationSolvingMethod {
    override fun solveEquation(equationParams: EquationParams): EquationResult {
        val f = equationParams.equation

        var iterations = 0u

        var xRes = (equationParams.a + equationParams.b) / 2
        var yRes = EquationService.calculateFunction(f, xRes)

        while (abs(yRes) > equationParams.epsilon) {
            val yFirstDer = EquationService.calculateDerivative(f, xRes)
            val ySecondDer = EquationService.calculateNDerivative(f, xRes, 2u)
            if (yRes * ySecondDer <= 0)
                throw LowEfficiencyMethodException()

            xRes -= yRes / yFirstDer
            yRes = EquationService.calculateFunction(f, xRes)
            iterations++
        }

        return EquationResult.ok(EquationSolvingMethod.NEWTON_METHOD, xRes, yRes, iterations)
    }
}