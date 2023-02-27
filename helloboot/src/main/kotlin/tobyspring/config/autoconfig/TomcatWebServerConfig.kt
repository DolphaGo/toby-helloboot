package tobyspring.config.autoconfig

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import tobyspring.config.MyAutoConfiguration

//@Configuration
@MyAutoConfiguration
@Conditional(TomcatCondition::class)
class TomcatWebServerConfig {

    @Bean("tomcatWebServerFactory")
    fun servletWebServerFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }

}

class TomcatCondition : Condition{
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return false
    }
}
