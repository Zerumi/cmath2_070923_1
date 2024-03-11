package io.github.project

import io.github.project.data.system.EquationSystemParams
import io.github.project.data.system.EquationSystemResult
import io.kvision.annotations.KVService

@KVService
interface IEquationSystemService {
    suspend fun solveEquationSystem(equationSystemParams: EquationSystemParams): EquationSystemResult
}
