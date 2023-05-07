package tobyspring.config.autoconfig

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
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
class TomcatWebServerConfig(
    /**
     * 주석
     * @Value("\${contextPath:}") val contextPath: String,
     * @Value("\${port:8080}") val port: Int // port라는 프로퍼티가 없으면 에러가 납니다. : 로 디폴트 값을 지정해줍시다.
     */
) {
    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    fun servletWebServerFactory(
//        env: Environment
        serverProperties: ServerProperties
    ): ServletWebServerFactory {
        // env로부터 가져오는 방법도 있다 -> factory.contextPath = env.getProperty("contextPath")
//        return TomcatServletWebServerFactory(contextPath, port)
        return TomcatServletWebServerFactory(serverProperties.contextPath, serverProperties.port)
    }

}

class TomcatCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        // Tomcat 이 있으면, True, 없으면 False
        return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.classLoader)
    }
}
