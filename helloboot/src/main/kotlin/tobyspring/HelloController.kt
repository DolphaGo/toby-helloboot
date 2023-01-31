package tobyspring

class HelloController {
    fun hello(name: String): String {
        val service = SimpleHelloService()
        return service.sayHello(name)
    }
}
