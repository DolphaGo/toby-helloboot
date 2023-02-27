package tobyspring.config.autoconfig

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import tobyspring.config.MyAutoConfiguration

//@Configuration
@MyAutoConfiguration
@Conditional(JettyCondition::class)
class JettyWebServerConfig {

    @Bean("jettyWebServerFactory")
    fun servletWebServerFactory(): ServletWebServerFactory {
        return JettyServletWebServerFactory()
    }

}

class JettyCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return true // * 빈으로 등록할 것인지, 무시할 것인지
    }
}
