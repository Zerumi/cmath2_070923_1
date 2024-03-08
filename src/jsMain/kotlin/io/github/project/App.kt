package io.github.project

import io.kvision.*
import io.kvision.core.AlignItems
import io.kvision.core.onClickLaunch
import io.kvision.html.*
import io.kvision.panel.hPanel
import io.kvision.panel.root
import io.kvision.theme.Theme
import io.kvision.theme.ThemeManager
import io.kvision.theme.themeSwitcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

class App : Application() {

    init {
        io.kvision.require("style.css")
        ThemeManager.init(initialTheme = Theme.DARK, remember = true)
    }

    override fun start(state: Map<String, Any>) {
        val root = root("kvapp") {
            //customTag("script", attributes = mapOf(Pair("src", "https://www.desmos.com/api/v1.8/calculator.js?apiKey=dcb31709b452b1cf9dc26972add0fda6")))
            mainView()
        }
        AppScope.launch {
            //val pingResult = Model.ping("Hello world from client!")
            //root.add(Span(pingResult))
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
