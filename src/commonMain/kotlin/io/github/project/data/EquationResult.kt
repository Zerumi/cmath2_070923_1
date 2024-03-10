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