package tobyspring.config

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils
import kotlin.reflect.jvm.jvmName

class MyOnClassCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val attrs = metadata.getAnnotationAttributes(ConditionalMyOnClass::class.jvmName)
        val value: String = attrs?.get("value").toString() // 여기서 Value는 Jetty나 Tomcat의 핵심클래스들을 의미한다.
        return ClassUtils.isPresent(value, context.classLoader)
    }

}
