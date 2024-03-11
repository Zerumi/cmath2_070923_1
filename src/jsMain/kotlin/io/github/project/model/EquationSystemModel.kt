package io.github.project.model

import io.github.project.IEquationSystemService
import io.github.project.data.system.EquationSystemParams
import io.github.project.data.system.EquationSystemResult
import io.kvision.remote.getService

object EquationSystemModel {
    private val equationSystemService = getService<IEquationSystemService>()

    suspend fun solveEquationSystemRequest(equationSystemParams: EquationSystemParams) : EquationSystemResult {
        return equationSystemService.solveEquationSystem(equationSystemParams)
    }
}
