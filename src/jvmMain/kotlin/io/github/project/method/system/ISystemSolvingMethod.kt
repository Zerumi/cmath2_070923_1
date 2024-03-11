package io.github.project.method.system

import io.github.project.data.system.EquationSystemParams
import io.github.project.data.system.EquationSystemResult

interface ISystemSolvingMethod {
    fun solveSystem(equationSystemParams: EquationSystemParams): EquationSystemResult
}