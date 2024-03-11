package io.github.project.view

import io.github.project.data.equation.EquationSolvingMethod
import io.github.project.util.availableEquationMethods
import io.github.project.util.sendEquationToApi
import io.kvision.core.Container
import io.kvision.core.Display
import io.kvision.form.form
import io.kvision.form.select.select
import io.kvision.form.text.textInput
import io.kvision.html.*
import io.kvision.panel.hPanel
import io.kvision.state.ObservableValue

fun Container.noLinearEquationSolverView() {
    hPanel {
        options(1) {
            equationInputPanel()
        }
        options(2) {
            graphShowPanel()
            solutionEquationPanel()
        }
    }
}

val equation = ObservableValue("")
val a = ObservableValue(-5.0)
val b = ObservableValue(5.0)
val epsilon = ObservableValue(0.005)
val sMethod = ObservableValue(EquationSolvingMethod.HALF_DIVISION_METHOD)

fun Container.equationInputPanel() {
    div(className = "input_element") {
        form {
            setStyle("padding", "3px")
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
                options = availableEquationMethods
                selectedIndex = 0
            }.subscribe {
                if (it != null) {
                    sMethod.setState(enumValueOf<EquationSolvingMethod>(it))
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

lateinit var solutionEquationPanel: Div

fun Container.solutionEquationPanel() {
    solutionEquationPanel = div(className = "solution_element") {
        p("Your solution will appear here...")
    }
}
