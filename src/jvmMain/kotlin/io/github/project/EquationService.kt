package io.github.project

import io.github.project.data.EquationParams
import io.github.project.data.EquationResult

actual class EquationService : IEquationService {
    override suspend fun solveEquation(equationParams: EquationParams): EquationResult {
        TODO("Not yet implemented")
    }
}