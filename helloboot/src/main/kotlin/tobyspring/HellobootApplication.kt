package tobyspring

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class HellobootApplication

fun main(args: Array<String>) {
//    val tomcat = Tomcat()
//    tomcat.start()

    /**
     * 톰캣 서블릿 웹서버를 생성하기 위한 스프링부트 도우미 클래스
     */
    val serverFactory = TomcatServletWebServerFactory()
    val webServer = serverFactory.getWebServer(ServletContextInitializer {
        it.addServlet(
            "hello",
            object : HttpServlet() {
                override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
                    val name = req?.getParameter("name")

                    resp?.status = HttpStatus.OK.value()
                    resp?.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE) // String 을 하드코딩하면 오타의 위험성이 있긴 하다.
                    resp?.writer?.println("Hello $name")
                }
            }
        ).addMapping("/hello") // 해당 매핑으로 요청이 오면 service가 동작한다.
    })

    webServer.start()
}

/**
 * 위 서버를 실행하면, 서블릿 컨테이너가 띄워지고, 서블릿을 직접 등록해서 동작시킬 수 있다.
 * 결과는 다음과 같다.
 */

//✗ http -v ":8080/hello?name=DolphaGo"
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
//Date: Mon, 23 Jan 2023 16:50:54 GMT
//Keep-Alive: timeout=60
//
//Hello DolphaGo

/**
 * 서블릿은 Request 와 Response 를 둘다 다뤄줘야 함.
 *
 * 서블릿은 각 URL에 맞게, 처리하는 방식으로 동작하도록 해야 하는데, 이를 중앙화된 제일 앞단에 컨트롤러를 두자 => FrontController
 *
 * 즉, 공통적인 부분은 제일 앞단에서 처리!
 *
 * 프론트 컨트롤러를 내장 => 인증/다국어 처리 등에 일반화되어 있음
 */
