package tobyspring.config

import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyAutoConfigImportSelector : DeferredImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        return arrayOf(
            "tobyspring.config.autoconfig.DispatcherServletConfig",
            "tobyspring.config.autoconfig.TomcatWebServerConfig"
        )
    }
}
