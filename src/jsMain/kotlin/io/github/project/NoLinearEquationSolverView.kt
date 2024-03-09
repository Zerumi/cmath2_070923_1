package io.github.project

import io.github.project.parse.parseStringDesmosLatex
import io.kvision.core.Container
import io.kvision.form.form
import io.kvision.form.text.textInput
import io.kvision.html.customTag
import io.kvision.html.div
import io.kvision.html.label
import io.kvision.html.p
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
val a = ObservableValue(-5)
val b = ObservableValue(5)
val epsilon = ObservableValue("")

fun Container.equationInputPanel() {
    div {
        form {
            textInput(init = {
                placeholder = "Enter equation here..."
            }).subscribe {
                equation.setState(it ?: "")
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
                    a.setState(it.toInt())
                }
            }
            textInput {
                placeholder = "Enter b..."
            }.subscribe {
                if (it != null && it.toIntOrNull() != null) {
                    b.setState(it.toInt())
                }
            }
            textInput {
                placeholder = "Enter epsilon (0.005 by default)..."
            }.subscribe {
                epsilon.setState(it ?: "0.005")
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

fun Container.solutionPanel() {
    div { p("solution") }
}
