package io.github.project

import io.github.project.data.EquationError
import io.github.project.data.equation.EquationParams
import io.github.project.data.equation.EquationResult
import io.github.project.data.equation.EquationSolvingMethod
import io.github.project.exception.CalculationException
import io.github.project.exception.MethodNotImplementedException
import io.github.project.exception.NoRootException
import io.github.project.exception.TooLowEpsilonException
import io.github.project.method.equation.HalfDivisionMethod
import io.github.project.method.equation.NewtonMethod
import io.github.project.method.equation.SimpleIterationMethod
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

actual class EquationService : IEquationService {
    companion object {
        fun validateEpsilon(epsilon: Double) {
            if (epsilon <= 0) throw TooLowEpsilonException()
        }

        fun validateFunction(equationParams: EquationParams) {
            validateEpsilon(equationParams.epsilon)

            val fA = calculateFunction(equationParams.equation, equationParams.a)
            val fB = calculateFunction(equationParams.equation, equationParams.b)

            if (fA * fB > 0) throw NoRootException()
        }

        fun calculateFunction(f: String, x: Double): Double {
            val argument = Argument("x = $x")
            val equation = Expression(f, argument)
            return equation.calculate()
        }

        fun calculateDerivative(f: String, x: Double): Double {
            val argument = Argument("x = $x")
            val equation = Expression("der(${f}, x)", argument)
            return equation.calculate()
        }

        fun calculateNDerivative(f: String, x: Double, n: UInt): Double {
            val argument = Argument("x = $x")
            val equation = Expression("dern(${f}, ${n}, x)", argument)
            return equation.calculate()
        }
    }

    private val methods = mapOf(
        Pair(EquationSolvingMethod.HALF_DIVISION_METHOD, HalfDivisionMethod()),
        Pair(EquationSolvingMethod.NEWTON_METHOD, NewtonMethod()),
        Pair(EquationSolvingMethod.SIMPLE_ITERATION_METHOD, SimpleIterationMethod()),
    )

    override suspend fun solveEquation(equationParams: EquationParams): EquationResult {
        try {
            validateFunction(equationParams)
            return methods[equationParams.equationSolvingMethod]?.solveEquation(equationParams)
                ?: throw MethodNotImplementedException()
        } catch (e: CalculationException) {
            return EquationResult.bad(
                EquationError(e.code, e.message), equationParams.equationSolvingMethod
            )
        }
    }
}