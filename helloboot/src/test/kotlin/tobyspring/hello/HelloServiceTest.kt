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
}
