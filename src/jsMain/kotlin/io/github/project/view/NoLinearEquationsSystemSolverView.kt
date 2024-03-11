package io.github.project.view

import io.github.project.data.system.EquationSystemSolvingMethod
import io.github.project.util.availableEquationSystemMethods
import io.github.project.util.sendEquationSystemToApi
import io.kvision.core.Container
import io.kvision.core.Display
import io.kvision.form.form
import io.kvision.form.select.select
import io.kvision.form.text.textInput
import io.kvision.html.*
import io.kvision.panel.hPanel
import io.kvision.state.ObservableValue
import io.kvision.state.observableListOf

fun Container.noLinearEquationsSystemSolverView() {
    hPanel {
        options(1) {
            inputSystemPanel()
        }
        options(2) {
            graphShowPanel()
            solutionSystemPanel()
        }
    }
}

val equations = observableListOf("","")
val startApproximationNames = observableListOf("x", "y")
val startApproximation = observableListOf(0.0, 0.0)
val sSystemMethod = ObservableValue(EquationSystemSolvingMethod.NEWTON_METHOD)

fun Container.inputSystemPanel() {
    div(className = "input_element") {
        form {
            setStyle("padding", "3px")
            div {
                textInput {
                    display = Display.INLINEBLOCK
                    setStyle("width", "calc(100% - 20px)")
                    placeholder = "Enter equation here..."
                }.subscribe {
                    equations[0] = it ?: ""
                }
                label("=0") {
                    display = Display.INLINEBLOCK
                }
            }
            div {
                textInput {
                    display = Display.INLINEBLOCK
                    setStyle("width", "calc(100% - 20px)")
                    placeholder = "Enter equation here..."
                }.subscribe {
                    equations[1] = it ?: ""
                }
                label("=0") {
                    display = Display.INLINEBLOCK
                }
            }
            textInput {
                placeholder = "Enter x..."
            }.subscribe {
                if (it != null && it.toIntOrNull() != null) {
                    startApproximation[0] = it.toDouble()
                }
            }
            textInput {
                placeholder = "Enter y..."
            }.subscribe {
                if (it != null && it.toIntOrNull() != null) {
                    startApproximation[1] = it.toDouble()
                }
            }
            textInput {
                placeholder = "Enter epsilon (0.005)..."
            }.subscribe {
                if (it != null && it.toDoubleOrNull() != null) {
                    epsilon.setState(it.toDouble())
                } else epsilon.setState(0.005)
            }
            select {
                options = availableEquationSystemMethods
                selectedIndex = 0
            }.subscribe {
                if (it != null) {
                    sSystemMethod.setState(enumValueOf<EquationSystemSolvingMethod>(it))
                }
            }
            button("Submit") {
                onClick(handler = sendEquationSystemToApi)
            }
        }
    }
}

lateinit var solutionSystemPanel : Div

fun Container.solutionSystemPanel() {
    solutionSystemPanel = div(className = "solution_element") {
        p("Your solution will appear here...")
    }
}
