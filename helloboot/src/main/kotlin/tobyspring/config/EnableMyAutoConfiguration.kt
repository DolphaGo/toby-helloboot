package tobyspring.config

import org.springframework.context.annotation.Import


/**
 * Import 라는 것도 메타 어노테이션으로 관리가 가능하다. import 문도 메타 어노테이션의 메타 어노테이션으로도 가능하다.
 * 근데, import 를 이렇게 계속해서 길게 작성할 것인가? 동적으로 가져오도록 해보자.
 * 동적으로 가져오기 위해서는 Import 가 아닌 ImportSelector가 필요하다.
 */
//@Import(DispatcherServletConfig::class, TomcatWebServerConfig::class) // Component Annotation 이 붙거나 이게 붙은 메타 어노테이션들을 직접 추가할 수가 있다.
@Import(MyAutoConfigImportSelector::class) // importSelector 인터페이스를 구현한 클래스를 가져오면, 그 내부 메서드를 실행시켜서 나오는 String 을 따라가서 클래스들을 찾아서 import 해준다.
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS) // 어노테이션 클래스에도 붙이려면, 코틀린에서는 CLASS 타겟이라고 붙여줘야 하는 것 같다.
annotation class EnableMyAutoConfiguration
