package io.github.project.util

import io.github.project.data.SolvingMethod

val availableMethods = SolvingMethod.entries.map { it.name to it.displayName }.toList()