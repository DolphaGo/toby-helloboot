package tobyspring.hello

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// 스프링 컨테이너의 역할 : 메타 정보를 받아서 직접 오브젝트를 생성한다.
// 게다가 Assembler의 역할을 한다. 오브젝트를 주입해주는 역할
// HelloController가 HelloService를 볼 때, SimpleHelloService, ComplexHelloService 를 HelloService를 바라보도록 한다.
//@RestController : DispatcherServlet 과 직접적인 관련은 없습니다.
//@RequestMapping("/hello") // 스프링 부트 3.0 이전까지 DispatcherServlet 이 path를 찾아가는 방법
/**
 * 부트 3.0과 스프링 6.0 이전 버전에서는 매핑테이블에 핸들러로 등록되기 위해서는 Class Level의 @RequestMapping 어노테이션만으로도 가능했으나,
 * 3.0 부터는 클래스 레벨의 핸들러 감지 대상에 @Controller 어노테이션만 포함된다.
 */
//@RequestMapping("/hello")
//@RestController
//@Component
@RestController // 클래스 레벨에 RequestMapping 이 없어도 이 메타어노테이션으로도 스프링에서 Path를 뒤져준다.(스프링 부트 3.0 이전!)
//@MyComponent
class HelloController(
    private val service: HelloService
) : ApplicationContextAware {

    private lateinit var applicationContext : ApplicationContext

    // Method Level : GET (요청 방법) + PATH
    @GetMapping("/hello")
//    @ResponseBody // 리턴하는 값이 뷰 이름이 아니고 리턴하는 타입으로 하려고 할 때
//    @RequestMapping(value = ["/hello"], method = [RequestMethod.GET])
    fun hello(name: String?): String {
        if(name.isNullOrBlank()){
            throw IllegalArgumentException()
        }
        return service.sayHello(name)
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        println(applicationContext) // 자동으로 호출이 된다.. (Root WebApplicationContext, started on Sun Feb 12 23:43:21 KST 2023)
        this.applicationContext = applicationContext // 물론 컨트롤러 생성자로도 주입이 가능하다
    }
}
