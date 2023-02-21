package tobyspring.hello

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HelloServiceTest{

    @Test
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
