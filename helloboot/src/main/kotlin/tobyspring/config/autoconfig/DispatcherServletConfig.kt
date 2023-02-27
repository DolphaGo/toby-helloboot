package tobyspring.config.autoconfig

import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.DispatcherServlet
import tobyspring.config.MyAutoConfiguration

@MyAutoConfiguration
//@Configuration
class DispatcherServletConfig {
    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        return DispatcherServlet()
    }
}
