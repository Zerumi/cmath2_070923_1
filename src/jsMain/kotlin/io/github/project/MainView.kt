package io.github.project

import io.kvision.core.*
import io.kvision.dropdown.dropDown
import io.kvision.form.check.checkBox
import io.kvision.form.text.text
import io.kvision.html.*
import io.kvision.navbar.nav
import io.kvision.navbar.navForm
import io.kvision.navbar.navbar
import io.kvision.panel.*
import io.kvision.theme.themeSwitcher

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
    navbar("cmath2_070324_1") {
        nav {
            tag(TAG.LI) {
                link("File", icon = "fa-file")
            }
            tag(TAG.LI) {
                link("Edit", icon = "fa-bars")
            }
            dropDown(
                "Favourites",
                listOf("Basic formatting" to "#!/basic", "Forms" to "#!/forms"),
                icon = "fa-star",
                forNavbar = true
            )
        }
        navForm {
            text(label = "Search:")
            checkBox()
        }
        nav(rightAlign = true) {
            tag(TAG.LI) {
                themeSwitcher(style = ButtonStyle.OUTLINESECONDARY, round = true)
            }
        }
    }
}

