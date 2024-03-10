package io.github.project

import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.routing.*
import io.kvision.remote.applyRoutes
import io.kvision.remote.getAllServiceManagers
import io.kvision.remote.kvisionInit
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.mariuszgromada.math.mxparser.License
import org.mariuszgromada.math.mxparser.mXparser

fun Application.main() {
    install(Compression)
    routing {
        getAllServiceManagers().forEach { applyRoutes(it) }
    }
    val module = module {
        factoryOf(::PingService)
        factoryOf(::EquationService)
    }
    kvisionInit(module)

    License.iConfirmNonCommercialUse("367837@edu.itmo.ru")
    mXparser.disableAlmostIntRounding()
    mXparser.disableUlpRounding()
    mXparser.enableCanonicalRounding()
}
