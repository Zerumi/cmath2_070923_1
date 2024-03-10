package io.github.project.view

import io.github.project.data.SolvingMethod
import io.github.project.parse.parseStringDesmosLatex
import io.github.project.util.availableMethods
import io.github.project.util.sendEquationToApi
import io.kvision.core.Container
import io.kvision.core.Display
import io.kvision.form.form
import io.kvision.form.select.select
import io.kvision.form.text.textInput
import io.kvision.html.*
import io.kvision.panel.hPanel
import io.kvision.state.ObservableValue
import io.kvision.state.bind

fun Container.noLinearEquationSolverView() {
    hPanel {
        options(1) {
            equationInputPanel()
        }
        options(2) {
            graphShowPanel()
            solutionPanel()
        }
    }
}

val equation = ObservableValue("")
val a = ObservableValue(-5.0)
val b = ObservableValue(5.0)
val epsilon = ObservableValue(0.005)
val sMethod = ObservableValue(SolvingMethod.HALF_DIVISION_METHOD)

fun Container.equationInputPanel() {
    div (className = "input_element") {
        form {
            div {
                textInput {
                    display = Display.INLINEBLOCK
                    setStyle("width", "calc(100% - 20px)")
                    placeholder = "Enter equation here..."
                }.subscribe {
                    equation.setState(it ?: "")
                }
                label("=0") {
                    display = Display.INLINEBLOCK
                }
            }
            label {
                bind(equation) { state ->
                    +"Your input:\n ${parseStringDesmosLatex(state)}"
                }
            }
            textInput {
                placeholder = "Enter a..."
            }.subscribe {
                if (it != null && it.toIntOrNull() != null) {
                    a.setState(it.toDouble())
                }
            }
            textInput {
                placeholder = "Enter b..."
            }.subscribe {
                if (it != null && it.toIntOrNull() != null) {
                    b.setState(it.toDouble())
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
                options = availableMethods
                selectedIndex = 0
            }.subscribe {
                if (it != null) {
                    sMethod.setState(enumValueOf<SolvingMethod>(it))
                }
            }
            button("Submit") {
                onClick(handler = sendEquationToApi)
            }
        }
    }
}

// it calls "writing front on Kotlin" :)
fun Container.graphShowPanel() {
    customTag(
        "div", className = "graph_element", attributes = mapOf(Pair("id", "graph"))
    )
    customTag("script", attributes = mapOf(Pair("src", "graph.js")))
}

lateinit var solutionPanel: Div

fun Container.solutionPanel() {
    solutionPanel = div (className = "solution_element") {
        p("Your solution will appear here...")
    }
}
