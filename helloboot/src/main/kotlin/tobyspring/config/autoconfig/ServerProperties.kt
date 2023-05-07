package tobyspring.config.autoconfig

import tobyspring.config.MyConfigurationProperties

@MyConfigurationProperties(prefix = "server")
data class ServerProperties(
    val contextPath: String?,
    val port: Int?
)
