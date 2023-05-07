package tobyspring.config.autoconfig

import org.springframework.boot.context.properties.bind.Binder
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import tobyspring.config.MyAutoConfiguration

@MyAutoConfiguration
class ServerPropertiesConfig {

    @Bean
    fun serverProperties(
        env: Environment
    ): ServerProperties {
        // 클래스의 변수명과 프로퍼티가 이름이 동일한 것을 찾아서 매핑을 해준다.
        return Binder.get(env).bind("", ServerProperties::class.java).get()
//
//        val contextPath = env.getProperty("contextPath") ?: ""
//        val port = env.getProperty("port")?.toInt() ?: 8082
//        return ServerProperties(contextPath, port)
    }
}
