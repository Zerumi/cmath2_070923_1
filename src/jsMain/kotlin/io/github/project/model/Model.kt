package io.github.project.model

import io.github.project.IPingService
import io.kvision.remote.getService

object Model {

    private val pingService = getService<IPingService>()

    suspend fun ping(message: String): String {
        return pingService.ping(message)
    }
}
