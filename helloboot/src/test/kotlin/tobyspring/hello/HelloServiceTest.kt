package tobyspring.hello

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class UnitTest

/**
 * 메타 어노테이션을 여러개 합침 => Composed Annotation
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@UnitTest
annotation class FastUnitTest

class HelloServiceTest{

    @FastUnitTest
//    @UnitTest
    fun simpleHelloService() {
        // given
        val helloService = SimpleHelloService()

        // when
        val response = helloService.sayHello("DolphaGo")

        // then
        Assertions.assertThat(response).isEqualTo("Hello DolphaGo")
    }

    @Test
    fun helloDecorator() {
        // given
        val decorator = HelloDecorator(object : HelloService {
            override fun sayHello(name: String): String {
                return "Hello $name"
            }
        })

        // when
        val response = decorator.sayHello("DolphaGo")

        // then
        Assertions.assertThat(response).isEqualTo("*Hello DolphaGo*")
    }
}
