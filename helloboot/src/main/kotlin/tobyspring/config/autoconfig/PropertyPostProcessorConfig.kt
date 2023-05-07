package tobyspring.config.autoconfig

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.env.Environment
import tobyspring.config.MyAutoConfiguration
import tobyspring.config.MyConfigurationProperties

@MyAutoConfiguration
class PropertyPostProcessorConfig {

    @Bean
    fun propertyPostProcessor(env: Environment): BeanPostProcessor {
        return object : BeanPostProcessor {
            override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
                val annotation = AnnotationUtils.findAnnotation(bean.javaClass, MyConfigurationProperties::class.java) ?: return bean

                val attrs = AnnotationUtils.getAnnotationAttributes(annotation)
                val prefix = attrs["prefix"] as String

                return Binder.get(env).bindOrCreate(prefix, bean.javaClass) // 바인딩을 시도했는데, 없으면 빈을 생성해서 리턴한다는 의미임.
            }
        }
    }
}
