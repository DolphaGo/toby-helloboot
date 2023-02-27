package tobyspring.config.autoconfig

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import tobyspring.config.MyAutoConfiguration

//@Configuration
@MyAutoConfiguration
class TomcatWebServerConfig {

    @Bean
    fun servletWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }

}
