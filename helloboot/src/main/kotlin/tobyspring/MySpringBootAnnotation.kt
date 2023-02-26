package tobyspring

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@ComponentScan
@Configuration
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS) // Type : class, interface, enum 3가지를 모두 포함시킬 때 (근데 왜 코틀린으로는 TYPE 이 클래스에 안먹니.)
@Retention(AnnotationRetention.RUNTIME) // 원래 자바 어노테이션의 디폴트 어노테이션은 Class다.
// 어노테이션 정보가 컴파일된 클래스 파일까지는 살아있지만, 다른 클래스의 런타임에 로딩될 땐 사라지게 되기 때문이다!!
annotation class MySpringBootAnnotation
