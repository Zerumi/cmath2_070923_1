package io.github.project

import io.github.project.data.EquationParams
import io.kvision.html.Button
import io.kvision.html.br
import io.kvision.html.div
import io.kvision.html.label
import kotlinx.coroutines.launch
import org.w3c.dom.events.MouseEvent

val sendEquationToApi : Button.(MouseEvent) -> Unit = {
    val equationParams = EquationParams(equation.getState(),
        a.getState(),
        b.getState(),
        epsilon.getState(),
        method.getState())
    AppScope.launch {
        val result = EquationModel.sendSolveEquationRequest(equationParams)
        solutionPanel.clearParent()
        solutionPanel.add(
            if (result.validResult) {
                div {
                    label("Solution: " + result.result + " (function result : " + result.functionResult + ")")
                    br()
                    label("Solved by method: " + result.solvingMethod)
                    br()
                    label("Amount of iterations: " + result.amountOfIterations)
                }
            }
            else {
                div {
                    label("Error: " + result.errorObject.errorMessage)
                }
            }
        )
    }
}
