package io.github.project.model

import io.github.project.IEquationService
import io.github.project.data.EquationParams
import io.github.project.data.EquationResult
import io.kvision.remote.getService

object EquationModel {
    private val equationService = getService<IEquationService>()

    suspend fun sendSolveEquationRequest(equationParams: EquationParams): EquationResult {
        return equationService.solveEquation(equationParams)
    }
}