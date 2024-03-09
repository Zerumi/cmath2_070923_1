package io.github.project

import io.github.project.data.EquationParams
import io.github.project.data.EquationResult
import io.kvision.annotations.KVService

@KVService
interface IEquationService {
    suspend fun solveEquation(equationParams: EquationParams) : EquationResult
}