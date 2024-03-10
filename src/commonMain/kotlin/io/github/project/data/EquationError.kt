package io.github.project.data

import kotlinx.serialization.Serializable

@Serializable
data class EquationError (
    val errorCode : Int,
    val errorMessage : String
) {
    companion object {
        fun ok() : EquationError {
            return EquationError(0, "Successfully calculated")
        }
    }
}