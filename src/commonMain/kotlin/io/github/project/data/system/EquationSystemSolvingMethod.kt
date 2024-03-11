package io.github.project.data.system

import kotlinx.serialization.Serializable

@Serializable
enum class EquationSystemSolvingMethod(val displayName: String) {
    NEWTON_METHOD("Newton Method"),
}
