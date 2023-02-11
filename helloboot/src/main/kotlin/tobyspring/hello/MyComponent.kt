package tobyspring.hello

import org.springframework.stereotype.Component

@Component
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS) // 클래스나 인터페이스 같은 타입에 적용될 것
@Retention(AnnotationRetention.RUNTIME)
annotation class MyComponent
