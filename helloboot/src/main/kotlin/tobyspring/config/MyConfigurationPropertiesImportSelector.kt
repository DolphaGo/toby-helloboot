package tobyspring.config

import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata
import kotlin.reflect.jvm.jvmName

class MyConfigurationPropertiesImportSelector : DeferredImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {

        val attr = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties::class.jvmName)
        val propertyClass = attr?.getFirst("value") as Class<*>

        return arrayOf(propertyClass.name)
    }

}
