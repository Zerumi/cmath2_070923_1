package io.github.project

import io.kvision.core.Container
import io.kvision.form.text.textInput
import io.kvision.html.customTag
import io.kvision.html.div
import io.kvision.html.p
import io.kvision.panel.hPanel

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

fun Container.equationInputPanel() {
    div {
        textInput {
            placeholder = "Enter equation here..."
        }
    }
}

// it calls "writing front on Kotlin :)
fun Container.graphShowPanel() {
    customTag("div", className = "graph_element",
        attributes = mapOf(Pair("id", "graph")))
    // fixme:
    // define graph somewhere else
    // and here call a function that makes desmos graph from created element
    customTag("script", attributes = mapOf(Pair("src", "graph.js")))
}

fun Container.solutionPanel() {
    div { p("solution") }
}