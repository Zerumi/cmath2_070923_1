package io.github.project.view

import io.github.project.style.borderStyle
import io.kvision.core.Container
import io.kvision.html.ButtonStyle
import io.kvision.html.TAG
import io.kvision.html.header
import io.kvision.html.tag
import io.kvision.navbar.nav
import io.kvision.navbar.navbar
import io.kvision.panel.SideTabSize
import io.kvision.panel.TabPosition
import io.kvision.panel.tab
import io.kvision.panel.tabPanel
import io.kvision.theme.themeSwitcher

fun Container.mainView() {
    header {
        addCssStyle(borderStyle)
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
    navbar("cmath2_070324_1") {
        nav(rightAlign = true) {
            tag(TAG.LI) {
                themeSwitcher(style = ButtonStyle.OUTLINESECONDARY, round = true)
            }
        }
    }
}

