package tobyspring

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer

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
                    resp?.status = 200
                    resp?.setHeader("Content-Type", "text/plain")
                    resp?.writer?.println("Hello Servlet")
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

//http -v ":8080/hello"
//GET /hello HTTP/1.1
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
//Content-Length: 14
//Content-Type: text/plain;charset=ISO-8859-1
//Date: Mon, 23 Jan 2023 16:43:47 GMT
//Keep-Alive: timeout=60
//
//Hello Servlet
