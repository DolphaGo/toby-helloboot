package tobyspring.hello

import org.springframework.stereotype.Component

@Component
class SimpleHelloService : HelloService {

    override fun sayHello(name: String) : String {
        return "Hello $name"
    }
}
