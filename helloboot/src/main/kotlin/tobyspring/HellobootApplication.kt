package tobyspring

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory

class HellobootApplication

fun main(args: Array<String>) {
//    val tomcat = Tomcat()
//    tomcat.start()

    /**
     * 톰캣 서블릿 웹서버를 생성하기 위한 스프링부트 도우미 클래스
     */
    val serverFactory = TomcatServletWebServerFactory()
    val webServer = serverFactory.getWebServer()
    webServer.start()
}

