package tobyspring.hello

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary // HelloService 타입 2개 중 먼저 사용
class HelloDecorator(
    private val helloService: HelloService // 자기 자신을 제외하면 나머지 1개가 명확
) : HelloService {

    override fun sayHello(name: String): String = "*" + helloService.sayHello(name) + "*" // 장식 추가

}
