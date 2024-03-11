package io.github.project

import io.github.project.data.equation.EquationParams
import io.github.project.data.equation.EquationResult
import io.kvision.annotations.KVService

@KVService
interface IEquationService {
    suspend fun solveEquation(equationParams: EquationParams): EquationResult
}
