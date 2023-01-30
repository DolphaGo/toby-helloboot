package tobyspring

// 스프링 컨테이너의 역할 : 메타 정보를 받아서 직접 오브젝트를 생성한다.
// 게다가 Assembler의 역할을 한다. 오브젝트를 주입해주는 역할
// HelloController가 HelloService를 볼 때, SimpleHelloService, ComplexHelloService 를 HelloService를 바라보도록 한다.
class HelloController(
    private val service: HelloService
) {
    fun hello(name: String): String {
        return service.sayHello(name)
    }
}
