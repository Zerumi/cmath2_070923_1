package io.github.project.data.system

import io.github.project.data.EquationError
import kotlinx.serialization.Serializable

@Serializable
data class EquationSystemResult(
    val validResult: Boolean,
    val equationSystemError: EquationError,
    val equationSolvingMethod: EquationSystemSolvingMethod,
    val resultVector: List<Double>,
    val amountOfIterations: UInt,
    val errorVector: List<Double>,
) {
    companion object {
        fun ok(
            equationSolvingMethod: EquationSystemSolvingMethod,
            resultVector: List<Double>,
            amountOfIterations: UInt,
            errorVector: List<Double>
        ): EquationSystemResult {
            return EquationSystemResult(
                true, EquationError.ok(), equationSolvingMethod, resultVector, amountOfIterations, errorVector
            )
        }

        fun bad(
            equationSystemError: EquationError,
            equationSolvingMethod: EquationSystemSolvingMethod
        ): EquationSystemResult {
            return EquationSystemResult(
                false, equationSystemError, equationSolvingMethod, emptyList(), 0u, emptyList()
            )
        }
    }
}
