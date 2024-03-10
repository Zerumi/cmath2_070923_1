package io.github.project.util

import io.github.project.AppScope
import io.github.project.data.EquationParams
import io.github.project.model.EquationModel
import io.github.project.view.*
import io.kvision.html.Button
import io.kvision.html.br
import io.kvision.html.div
import io.kvision.html.label
import kotlinx.coroutines.launch
import org.w3c.dom.events.MouseEvent

val sendEquationToApi: Button.(MouseEvent) -> Unit = {
    val equationParams = EquationParams(
        equation.getState(), a.getState(), b.getState(), epsilon.getState(), sMethod.getState()
    )
    AppScope.launch {
        val result = EquationModel.sendSolveEquationRequest(equationParams)
        solutionPanel.removeAll()
        solutionPanel.add(if (result.validResult) {
            div {
                label("Solution: " + result.result + " (function result : " + result.functionResult + ")")
                br()
                label("Solved by method: " + result.solvingMethod)
                br()
                label("Amount of iterations: " + result.amountOfIterations)
            }
        } else {
            div {
                label("Error: " + result.errorObject.errorMessage)
            }
        })
    }
}
