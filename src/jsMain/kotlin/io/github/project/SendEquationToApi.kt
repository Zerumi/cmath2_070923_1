package io.github.project

import io.github.project.data.EquationParams
import io.kvision.html.Button
import kotlinx.coroutines.launch
import org.w3c.dom.events.MouseEvent

val sendEquationToApi : Button.(MouseEvent) -> Unit = {
    val equationParams = EquationParams(equation.getState(), a.getState(), b.getState(), epsilon.getState())
    AppScope.launch {
        val result = EquationModel.sendSolveEquationRequest(equationParams)
        println(result)
    }
}
