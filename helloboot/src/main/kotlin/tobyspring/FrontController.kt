package tobyspring

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.util.function.Supplier

class FrontController

fun main(args: Array<String>) {
    val applicationContext = GenericApplicationContext()
    // 스프링 컨테이너는 어떤 클래스의 메타정보를 넣어주는 방식으로 빈을 생성한다.
    // 스프링 컨테이너는 싱글톤 방식으로 동작하기에 싱글톤 레지스트리라고도 한다.
    // 동일한 빈을 여러 서블릿 컨테이너에서 사용할 수가 있다.
    applicationContext.registerBean(HelloController::class.java, Supplier { HelloController() })
    applicationContext.refresh() // 컨테이너 초기화

    val serverFactory = TomcatServletWebServerFactory()
    val webServer = serverFactory.getWebServer(ServletContextInitializer {
//        val helloController = HelloController()

        it.addServlet(
            "frontcontroller",
            object : HttpServlet() {
                override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
                    // 인증, 보안, 다국어, 공통 기능
                    if (req.requestURI.equals("/hello") && req.method == HttpMethod.GET.name()) {
                        val name = req.getParameter("name")

                        val helloController = applicationContext.getBean(HelloController::class.java)
                        val result = helloController.hello(name) // 바인딩이라고 한다. 웹 요청으로 받은 것을 처리하는 오브젝트에서 사용할 수 있도록 하는 것.

//                        resp.status = HttpStatus.OK.value() // 서블릿에서 특별히 오류를 내지 않으면 200 OK가 리턴이 되긴한다. (Default 값임)
//                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE) // 이와 같은 방법보다 아래의 방법이 더 좋음
                        resp.contentType = MediaType.TEXT_PLAIN_VALUE
                        resp.writer?.println(result)
                    } else if (req.requestURI.equals("/user")) {
                        resp.status = HttpStatus.ACCEPTED.value()
                    } else {
                        resp.status = HttpStatus.NOT_FOUND.value()
                    }
                }
            }
        ).addMapping("/*") // 모든 요청을 프론트 컨트롤러가 받는다. (중앙 집중화)
    })

    webServer.start()
}

/**
 * 위의 결과는 다음과 같다.
 */
//~ http -v ":8080/hello?name=DolphaGo"
//GET /hello?name=DolphaGo HTTP/1.1
//Accept: */*
//Accept-Encoding: gzip, deflate
//Connection: keep-alive
//Host: localhost:8080
//User-Agent: HTTPie/3.2.1
//
//
//
//HTTP/1.1 200
//Connection: keep-alive
//Content-Length: 15
//Content-Type: text/plain;charset=ISO-8859-1
//Date: Mon, 23 Jan 2023 17:36:28 GMT
//Keep-Alive: timeout=60
//
//Hello DolphaGo
