package tobyspring.config

import org.springframework.boot.context.annotation.ImportCandidates
import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyAutoConfigImportSelector(
    private val classLoader: ClassLoader
) : DeferredImportSelector {

    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        // ApplicationContextAware interface 구현 -> Container가 Bean 생성한 것 처럼
        // classLoader 를 구현하는 방법으로 BeanClassLoaderAware interface 를 구현해서 BeanClassLoader 를 구현하는 방법도 있음

        val autoConfigs = arrayListOf<String>()
        val candidates: Iterable<String> = ImportCandidates.load(MyAutoConfiguration::class.java, classLoader)

        // 다음과 같이 표현할 수 있지만, ㅎㅎ 읽기가 어려울 수도 있으니.
//        return StreamSupport.stream(candidates.spliterator(), false).toArray { i -> arrayOfNulls<String>(i) }
        candidates.forEach { autoConfigs.add(it) }

        return autoConfigs.toArray(arrayOf())
    }
}
