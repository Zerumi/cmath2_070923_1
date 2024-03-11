package io.github.project.data.equation

import io.github.project.data.EquationError
import kotlinx.serialization.Serializable

@Serializable
data class EquationResult(
    val validResult: Boolean,
    val errorObject: EquationError,
    val equationSolvingMethod: EquationSolvingMethod,
    val result: Double,
    val functionResult: Double,
    val amountOfIterations: UInt,
) {
    companion object {
        fun ok(
            equationSolvingMethod: EquationSolvingMethod,
            result: Double,
            functionResult: Double,
            amountOfIterations: UInt
        ): EquationResult {
            return EquationResult(
                true, EquationError.ok(), equationSolvingMethod, result, functionResult, amountOfIterations
            )
        }

        fun bad(errorObject: EquationError, equationSolvingMethod: EquationSolvingMethod): EquationResult {
            return EquationResult(
                false,
                errorObject,
                equationSolvingMethod,
                0.0,
                0.0,
                0u,
            )
        }
    }
}
