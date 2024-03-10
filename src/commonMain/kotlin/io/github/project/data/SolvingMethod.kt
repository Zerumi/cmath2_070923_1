package io.github.project.data

import kotlinx.serialization.Serializable

@Serializable
enum class SolvingMethod(val displayName : String) {
    HALF_DIVISION_METHOD("Half Division Method"),
    NEWTON_METHOD("Newton Method"),
    SIMPLE_ITERATION_METHOD("Simple Iteration Method"),
}