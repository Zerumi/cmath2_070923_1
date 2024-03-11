package io.github.project

import io.github.project.data.EquationError
import io.github.project.data.system.EquationSystemParams
import io.github.project.data.system.EquationSystemResult
import io.github.project.data.system.EquationSystemSolvingMethod
import io.github.project.exception.CalculationException
import io.github.project.exception.MethodNotImplementedException
import io.github.project.method.system.NewtonMethod

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class EquationSystemService : IEquationSystemService {

    companion object {
        fun validateSystem(equationSystemParams: EquationSystemParams) {
            EquationService.validateEpsilon(equationSystemParams.epsilon)
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
