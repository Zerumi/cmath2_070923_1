package io.github.project.util

import io.github.project.AppScope
import io.github.project.data.equation.EquationParams
import io.github.project.model.EquationModel
import io.github.project.parse.plainToBackParse
import io.github.project.view.*
import io.kvision.html.Button
import io.kvision.html.br
import io.kvision.html.div
import io.kvision.html.label
import kotlinx.coroutines.launch
import org.w3c.dom.events.MouseEvent

val sendEquationToApi: Button.(MouseEvent) -> Unit = {
    val equationParams = EquationParams(
        plainToBackParse(equation.getState()), a.getState(), b.getState(), epsilon.getState(), sMethod.getState()
    )
    AppScope.launch {
        val result = EquationModel.sendSolveEquationRequest(equationParams)
        solutionEquationPanel.removeAll()
        solutionEquationPanel.add(if (result.validResult) {
            div {
                label("Solution: ${result.result}")
                br()
                label("Function Result: ${result.functionResult}")
                br()
                label("Solved by method: ${result.equationSolvingMethod}")
                br()
                label("Amount of iterations: ${result.amountOfIterations}")
                br()
            }
        } else {
            div {
                label("Error: ")
                br()
                label(result.errorObject.errorMessage)
                br()
            }
        })
    }
}
