package tobyspring.config.autoconfig

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.env.Environment
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils
import tobyspring.config.ConditionalMyOnClass
import tobyspring.config.MyAutoConfiguration

/**
 * 자주 사용되는 패턴
 * Class Level: 라이브러리가 있는 지 체크 (Conditional)
 * Bean Level: 개발자가 구성정보를 만들었는가, ConditionalOnMissingBean
 */
//@Configuration
@MyAutoConfiguration
//@Conditional(TomcatCondition::class)
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
class TomcatWebServerConfig {

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    fun servletWebServerFactory(env: Environment): ServletWebServerFactory {
        val factory = TomcatServletWebServerFactory()
        factory.contextPath = env.getProperty("contextPath")
        return factory
    }

}

class TomcatCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        // Tomcat 이 있으면, True, 없으면 False
        return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.classLoader)
    }
}
