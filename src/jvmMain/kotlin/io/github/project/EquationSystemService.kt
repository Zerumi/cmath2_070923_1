package io.github.project

import io.github.project.data.EquationError
import io.github.project.data.system.EquationSystemParams
import io.github.project.data.system.EquationSystemResult
import io.github.project.data.system.EquationSystemSolvingMethod
import io.github.project.exception.CalculationException
import io.github.project.exception.MethodNotImplementedException
import io.github.project.method.system.NewtonMethod
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.Function

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class EquationSystemService : IEquationSystemService {

    companion object {
        fun validateSystem(equationSystemParams: EquationSystemParams) {
            EquationService.validateEpsilon(equationSystemParams.epsilon)
        }

        fun calculateDerivativeByVariable(equation : String, variable: String, variables: Map<String, Double>) : Double {
            val argument = variables.map { Argument("${it.key} = ${it.value}") }.toTypedArray()
            val expression = Expression("der($equation, $variable)", *argument)
            val result = expression.calculate()
            return result
        }

        fun calculateFunctionWithMultipleArguments(f: String, arguments : Map<String, Double>) : Double {
            val argumentsString = arguments.map { it.key }.joinToString()
            val valuesString = arguments.map { it.value }.joinToString()
            val function = Function("f($argumentsString) = $f")
            val expression = Expression("f($valuesString)", function)
            return expression.calculate()
        }
    }

    private val methods = mapOf(
        Pair(EquationSystemSolvingMethod.NEWTON_METHOD, NewtonMethod()),
    )

    override suspend fun solveEquationSystem(equationSystemParams: EquationSystemParams): EquationSystemResult {
        try {
            validateSystem(equationSystemParams)
            return methods[equationSystemParams.equationSystemSolvingMethod]?.solveSystem(equationSystemParams)
                ?: throw MethodNotImplementedException()
        } catch (e: CalculationException) {
            return EquationSystemResult.bad(
                EquationError(e.code, e.message),
                equationSystemParams.equationSystemSolvingMethod
            )
        }
    }
}
