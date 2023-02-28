package tobyspring.config

import org.springframework.context.annotation.Conditional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Conditional(MyOnClassCondition::class)
annotation class ConditionalMyOnClass(
    val value: String
)
