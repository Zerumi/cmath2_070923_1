package io.github.project

import io.kvision.core.*
import io.kvision.html.*
import io.kvision.panel.*

fun Container.mainView() {
    header {
        addCssStyle(headerStyle)
        headerView()
    }
    tabPanel(tabPosition = TabPosition.LEFT, sideTabSize = SideTabSize.SIZE_2) {
        tab("Solve non linear equation") {
            noLinearEquationSolverView()
        }
        tab("Solve non linear equations system") {
            noLinearEquationsSystemSolverView()
        }
    }
}

fun Container.headerView() {
    div {
        p("cmath2_070324_1")
    }
}

