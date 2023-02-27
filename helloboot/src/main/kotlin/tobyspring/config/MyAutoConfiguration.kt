package tobyspring.config

import org.springframework.context.annotation.Configuration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Configuration(proxyBeanMethods = false)
annotation class MyAutoConfiguration


/**
 * proxyBeanMethods = false 는 무얼 의미할까
 * @Configuration 어노테이션은 특이하다.
 *
 */
