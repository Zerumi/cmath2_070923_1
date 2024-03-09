package io.github.project.data

import io.kvision.types.Decimal
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class EquationResult(
    @Contextual
    val result : Decimal
)