package io.github.project.data

import kotlinx.serialization.Serializable

@Serializable
data class EquationResult(
    val validResult: Boolean,
    val errorObject: EquationError,
    val solvingMethod: SolvingMethod,
    val result: Double,
    val functionResult: Double,
    val amountOfIterations: UInt,
) {
    companion object {
        fun ok(
            solvingMethod: SolvingMethod,
            result: Double,
            functionResult: Double,
            amountOfIterations: UInt
        ): EquationResult {
            return EquationResult(
                true, EquationError.ok(), solvingMethod, result, functionResult, amountOfIterations
            )
        }

        fun bad(errorObject: EquationError, solvingMethod: SolvingMethod): EquationResult {
            return EquationResult(
                false,
                errorObject,
                solvingMethod,
                0.0,
                0.0,
                0u,
            )
        }
    }
}