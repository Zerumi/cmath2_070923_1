package io.github.project.util

import io.github.project.AppScope
import io.github.project.data.system.EquationSystemParams
import io.github.project.model.EquationSystemModel
import io.github.project.view.*
import io.kvision.html.Button
import io.kvision.html.br
import io.kvision.html.div
import io.kvision.html.label
import kotlinx.coroutines.launch
import org.w3c.dom.events.MouseEvent

val sendEquationSystemToApi: Button.(MouseEvent) -> Unit = {
    val equationSystemParams = EquationSystemParams(
        equations.getState(), startApproximationNames.zip(startApproximation).toMap(), epsilon.getState(),
        sSystemMethod.getState(),
    )

    AppScope.launch {
        val result = EquationSystemModel.solveEquationSystemRequest(equationSystemParams)
        solutionSystemPanel.removeAll()
        solutionSystemPanel.add(if (result.validResult) {
            div {
                label("Solution: [${result.resultVector.map { it.key to it.value }.toList().joinToString()}]")
                br()
                label("Solution error: [${result.errorVector.joinToString()}]")
                br()
                label("Solved by method: ${result.equationSolvingMethod.displayName}")
                br()
                label("Amount of iterations: ${result.amountOfIterations}")
                br()
            }
        } else {
            div {
                label("Error: ")
                br()
                label(result.equationSystemError.errorMessage)
                br()
            }
        })
    }
}