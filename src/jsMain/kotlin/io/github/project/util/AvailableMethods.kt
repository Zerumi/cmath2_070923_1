package io.github.project.util

import io.github.project.data.equation.EquationSolvingMethod
import io.github.project.data.system.EquationSystemSolvingMethod

val availableEquationMethods = EquationSolvingMethod.entries.map { it.name to it.displayName }.toList()

val availableEquationSystemMethods = EquationSystemSolvingMethod.entries.map { it.name to it.displayName }.toList()
