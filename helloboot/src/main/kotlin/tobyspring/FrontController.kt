package tobyspring

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class FrontController

fun main(args: Array<String>) {
    val serverFactory = TomcatServletWebServerFactory()
    val webServer = serverFactory.getWebServer(ServletContextInitializer {
        it.addServlet(
            "frontcontroller",
            object : HttpServlet() {
                override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
                    // 인증, 보안, 다국어, 공통 기능
                    if (req.requestURI.equals("/hello") && req.method == HttpMethod.GET.name()) {
                        val name = req.getParameter("name")

                        resp.status = HttpStatus.OK.value()
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                        resp.writer?.println("Hello $name")
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
