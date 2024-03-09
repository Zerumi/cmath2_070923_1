package io.github.project

import io.github.project.parse.parseStringDesmosLatex
import io.kvision.*
import io.kvision.panel.root
import io.kvision.theme.Theme
import io.kvision.theme.ThemeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

external fun setExpression(expression : String)
external fun setLeftBorder(a : Int)
external fun setRightBorder(b : Int)

class App : Application() {

    init {
        io.kvision.require("style.css")
        ThemeManager.init(initialTheme = Theme.DARK, remember = true)
    }

    override fun start(state: Map<String, Any>) {
        root("kvapp") {
            mainView()
        }
        AppScope.launch {
            //val pingResult = Model.ping("Hello world from client!")
            //root.add(Span(pingResult))
            equation.subscribe {
                setExpression(parseStringDesmosLatex(it))
            }
            a.subscribe {
                setLeftBorder(it)
            }
            b.subscribe {
                setRightBorder(it)
            }
        }
    }
}

fun main() {
    startApplication(
        ::App,
        module.hot,
        BootstrapModule,
        BootstrapCssModule,
        FontAwesomeModule,
        CoreModule
    )
}
