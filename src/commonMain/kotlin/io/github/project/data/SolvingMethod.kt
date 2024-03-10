package io.github.project.data

import kotlinx.serialization.Serializable

@Serializable
enum class SolvingMethod {
    HALF_DIVISION_METHOD, NEWTON_METHOD, SIMPLE_ITERATION_METHOD,
}