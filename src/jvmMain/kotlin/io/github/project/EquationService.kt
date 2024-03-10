package io.github.project

import io.github.project.data.EquationError
import io.github.project.data.EquationParams
import io.github.project.data.EquationResult
import io.github.project.data.SolvingMethod
import io.github.project.exception.*
import io.github.project.method.HalfDivisionMethod
import io.github.project.method.NewtonMethod
import io.github.project.method.SimpleIterationMethod
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression

actual class EquationService : IEquationService {
    companion object {
        fun validateFunction(equationParams: EquationParams) {
            if (equationParams.epsilon <= 0) throw TooLowEpsilonException()

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
        Pair(SolvingMethod.HALF_DIVISION_METHOD, HalfDivisionMethod()),
        Pair(SolvingMethod.NEWTON_METHOD, NewtonMethod()),
        Pair(SolvingMethod.SIMPLE_ITERATION_METHOD, SimpleIterationMethod()),
    )

    override suspend fun solveEquation(equationParams: EquationParams): EquationResult {
        try {
            validateFunction(equationParams)
            return methods[equationParams.solvingMethod]?.solveEquation(equationParams)
                ?: throw MethodNotImplementedException()
        } catch (e: CalculationException) {
            return EquationResult.bad(
                EquationError(e.code, e.message), equationParams.solvingMethod
            )
        }
    }
}