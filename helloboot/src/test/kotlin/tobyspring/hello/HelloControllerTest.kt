package tobyspring.hello

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class HelloControllerTest {

    @Test
    fun helloController() {
        // given
        val controller = HelloController(object : HelloService {
            override fun sayHello(name: String): String {
                return name
            }
        })

        // when
        val response = controller.hello("DolphaGo")

        // then
        assertThat(response).isEqualTo("DolphaGo")
    }

    @Test
    fun failHelloController() {
        // given
        val controller = HelloController(object : HelloService {
            override fun sayHello(name: String): String {
                return name
            }
        })

        // then
        assertThatThrownBy { controller.hello(null) }
            .isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy { controller.hello("              ") }
            .isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy { controller.hello("") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
