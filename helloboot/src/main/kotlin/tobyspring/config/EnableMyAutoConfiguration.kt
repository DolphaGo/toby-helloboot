package tobyspring.config

import org.springframework.context.annotation.Import
import tobyspring.config.autoconfig.DispatcherServletConfig
import tobyspring.config.autoconfig.TomcatWebServerConfig


/**
 * Import 라는 것도 메타 어노테이션으로 관리가 가능하다. import 문도 메타 어노테이션의 메타 어노테이션으로도 가능하다.
 */
@Import(DispatcherServletConfig::class, TomcatWebServerConfig::class) // Component Annotation 이 붙거나 이게 붙은 메타 어노테이션들을 직접 추가할 수가 있다.
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS) // 어노테이션 클래스에도 붙이려면, 코틀린에서는 CLASS 타겟이라고 붙여줘야 하는 것 같다.
annotation class EnableMyAutoConfiguration
