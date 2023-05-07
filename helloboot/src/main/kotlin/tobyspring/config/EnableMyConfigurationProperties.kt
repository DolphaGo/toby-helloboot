package tobyspring.config

import org.springframework.context.annotation.Import
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Import(MyConfigurationPropertiesImportSelector::class)
annotation class EnableMyConfigurationProperties(
    val value: KClass<*>
)
