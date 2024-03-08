package io.github.project

import io.kvision.core.Container
import io.kvision.form.text.textInput
import io.kvision.html.customTag
import io.kvision.html.div
import io.kvision.html.label
import io.kvision.html.p
import io.kvision.panel.hPanel
import io.kvision.state.ObservableValue
import io.kvision.state.bind

fun Container.noLinearEquationSolverView() {
    hPanel{
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

fun Container.equationInputPanel() {
    div {
        textInput(init =  {
            placeholder = "Enter equation here..."
        }).subscribe {
            equation.setState(it ?: "")
        }
        label {
            bind(equation) { state ->
                +state
            }
        }
    }
}

// it calls "writing front on Kotlin :)
fun Container.graphShowPanel() {
    customTag("div", className = "graph_element",
        attributes = mapOf(Pair("id", "graph")))
    customTag("script", attributes = mapOf(Pair("src", "graph.js")))
}

fun Container.solutionPanel() {
    div { p("solution") }
}
