package tobyspring.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * 만약 Config 로 직접 추가해줘야 하는 것들이 늘어나면 Import 문에 들어가는 것들이 굉장히 길어질 것이다.
 */
//@Import(DispatcherServletConfig::class, TomcatWebServerConfig::class) // Component Annotation 이 붙거나 이게 붙은 메타 어노테이션들을 직접 추가할 수가 있다.
@EnableMyAutoConfiguration // 따라서 위와 같이 Import 안에 여러개를 작성하지 않고, 이 메타 어노테이션으로 관리한다.
@ComponentScan
@Configuration
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS) // Type : class, interface, enum 3가지를 모두 포함시킬 때 (근데 왜 코틀린으로는 TYPE 이 클래스에 안먹니.)
@Retention(AnnotationRetention.RUNTIME) // 원래 자바 어노테이션의 디폴트 어노테이션은 Class다.
// 어노테이션 정보가 컴파일된 클래스 파일까지는 살아있지만, 다른 클래스의 런타임에 로딩될 땐 사라지게 되기 때문이다!!
annotation class MySpringBootApplication
