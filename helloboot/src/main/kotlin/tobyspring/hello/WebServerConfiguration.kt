package tobyspring.hello

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class WebServerConfiguration{

    /**
     * 우리가 사용자 구성정보에서 먼저 작성한 것이 AutoConfiguration 보다 항상 우선한다.
     * 따라서 TomcatWebServerConfig 는 나중에 처리되기 때문에 ConditionalOnMissingBean 이 되어서 AutoConfiguration 되지 않는다.
     */
    @Bean
    fun customerWebServerFactory() : ServletWebServerFactory {
        val serverFactory = TomcatServletWebServerFactory()
        serverFactory.port = 9090
        return serverFactory
    }
}
