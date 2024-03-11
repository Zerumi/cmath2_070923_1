package io.github.project

import io.github.project.parse.parseStringDesmosLatex
import io.github.project.view.*
import io.kvision.*
import io.kvision.panel.root
import io.kvision.theme.Theme
import io.kvision.theme.ThemeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.math.abs

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

external fun setExpression(expression: String)
external fun setLeftBorder(a: Double)
external fun setRightBorder(b: Double)

external fun setSystemExpression1(expression: String)
external fun setSystemExpression2(expression: String)

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
        }
        AppScope.launch {
            a.subscribe {
                setLeftBorder(it - abs(it) * 0.1)
            }
        }
        AppScope.launch {
            b.subscribe {
                setRightBorder(it + abs(it) * 0.1)
            }
        }
        AppScope.launch {
            equations.subscribe {
                if (equations.size != 2) return@subscribe
                setSystemExpression1("${parseStringDesmosLatex(it[0]).replace("=0","")}=0")
                setSystemExpression2("${parseStringDesmosLatex(it[1]).replace("=0","")}=0")
            }
        }
    }
}

fun main() {
    startApplication(
        ::App, module.hot, BootstrapModule, BootstrapCssModule, FontAwesomeModule, CoreModule,
    )
}
