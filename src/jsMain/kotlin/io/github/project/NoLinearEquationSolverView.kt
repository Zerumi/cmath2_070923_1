package io.github.project

import io.kvision.core.*
import io.kvision.form.text.textInput
import io.kvision.html.customTag
import io.kvision.html.div
import io.kvision.html.p
import io.kvision.html.tag
import io.kvision.panel.flexPanel
import io.kvision.panel.gridPanel
import io.kvision.panel.hPanel
import io.kvision.utils.perc
import io.kvision.utils.px

fun Container.noLinearEquationSolverView() {
    hPanel{
        options(1) {
            equationInputPanel()
        }
        options(2) {
            graphShowPanel()
            //solutionPanel()
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