package tobyspring.hello//package tobyspring
//
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
//import org.springframework.boot.web.servlet.ServletContextInitializer
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
//import org.springframework.web.servlet.DispatcherServlet
//
//@ComponentScan // 이 클래스가 있는 패키지부터 시작해서 하위 패키지까지 뒤져서, 컴포넌트 어노테이션이 있는 것을 빈으로 등록한다. 장점: 새로운 빈을 추가할 때, 매번 다시 직접 등록할 필요 없음. 편리함
//// 모든 방식에는 장/단점이 있는데, 단점으로는 나중에 진짜 빈으로 등록되어야 하는 빈이 어디에서 등록되는 지를 찾아보는 것이 번거로울 수가 있다.
//@Configuration // 구성 정보를 가지고 있는 클래스라는 걸 스프링 컨테이너에게 알려주기 위함이다.
//class FrontControllerApplication {
//
//    /**
//     * Bean의 생명주기 메소드 : Dispatcher Servlet도 Bean으로 등록하자.
//     */
//
//    @Bean
//    fun servletWebServerFactory(): ServletWebServerFactory {
//        return TomcatServletWebServerFactory()
//    }
//
//    /**
//     * DispatcherServlet 은 명시적으로 Setter 를 통해서 넣어주지 않아도 ApplicationContextAware 에서 자동으로 주입해준다.
//     */
//    @Bean
//    fun dispatcherServlet(): DispatcherServlet {
//        return DispatcherServlet()
//    }
//
//}
//
//fun main(args: Array<String>) {
//    val applicationContext = object : AnnotationConfigWebApplicationContext() {
//
//        @Suppress("INAPPLICABLE_JVM_NAME") // https://youtrack.jetbrains.com/issue/KT-31420
//        @JvmName(name = "다른이름을 지정해주세요 setClassLoader(classLoader: ClassLoader?)와 JVM signature가 겹쳐요")
//        override fun setClassLoader(classLoader: ClassLoader) {
//
//        }
//
//        override fun onRefresh() {
//            super.onRefresh()
//
////            val serverFactory = TomcatServletWebServerFactory()
//            val serverFactory = getBean(ServletWebServerFactory::class.java)
//            val dispatcherServlet = getBean(DispatcherServlet::class.java)
//
//            /**
//             * 하지만 다음과 같이 주석처리를 해도(applicationContext를 지정하지 않아도) 제대로 동작한다.
//             * 스프링 컨테이너가 자동으로 주입을 해주는 것입니다.
//             */
////            dispatcherServlet.setApplicationContext(this) // ApplicationContext 를 자기자신 (AnnotationConfigWebApplicationContext 로 지정함)
//
//            val webServer = serverFactory.getWebServer(ServletContextInitializer {
//                it.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*")
//            })
//            webServer.start()
//        }
//    }
//
//    applicationContext.apply {
//        register(FrontControllerApplication::class.java) // 자바 구성 정보 어플리케이션을 등록해준다.
//        refresh() // 템플릿 메서드 패턴을 사용.(일정한 순서에 의해서 작업들 호출 및 서브 클래스를 확장하는 방식). 템플릿 메서드 패턴은 상속을 통해 기능을 확장한 것
//    }
//}
//
//
////{
////    @Bean
////    fun helloController(helloService: HelloService): HelloController {
////        return HelloController(helloService)
////    }
////
////    @Bean
////    fun helloService(): HelloService { // 인터페이스 타입으로 리턴할 것
////        return SimpleHelloService()
////    }
////}
//
////fun main(args: Array<String>) {
////    val applicationContext = object : AnnotationConfigWebApplicationContext() {
////
////        @Suppress("INAPPLICABLE_JVM_NAME") // https://youtrack.jetbrains.com/issue/KT-31420
////        @JvmName(name = "다른이름을 지정해주세요 setClassLoader(classLoader: ClassLoader?)와 JVM signature가 겹쳐요")
////        override fun setClassLoader(classLoader: ClassLoader) {
////
////        }
////
////        override fun onRefresh() {
////            super.onRefresh()
////
////            val serverFactory = TomcatServletWebServerFactory()
////            val webServer = serverFactory.getWebServer(ServletContextInitializer {
////                it.addServlet("dispatcherServlet", DispatcherServlet(this)).addMapping("/*")
////            })
////            webServer.start()
////        }
////    }
////
////    applicationContext.apply {
////        register(FrontControllerApplication::class.java) // 자바 구성 정보 어플리케이션을 등록해준다.
////        refresh() // 템플릿 메서드 패턴을 사용.(일정한 순서에 의해서 작업들 호출 및 서브 클래스를 확장하는 방식). 템플릿 메서드 패턴은 상속을 통해 기능을 확장한 것
////    }
////}
//
//
//// class FrontControllerApplication
/////**
//// * 스프링 컨테이너로 통합
//// */
////fun main(args: Array<String>) {
////    val applicationContext = object : GenericWebApplicationContext() {
////        override fun onRefresh() {
////            super.onRefresh()
////
////            val serverFactory = TomcatServletWebServerFactory()
////            val webServer = serverFactory.getWebServer(ServletContextInitializer {
////                it.addServlet("dispatcherServlet", DispatcherServlet(this)).addMapping("/*")
////            })
////            webServer.start()
////        }
////    }
////
////    applicationContext.apply {
////        registerBean(HelloService::class.java, Supplier { SimpleHelloService() })
////        registerBean(HelloController::class.java, Supplier { HelloController(applicationContext.getBean(HelloService::class.java)) })
////        refresh() // 템플릿 메서드 패턴을 사용.(일정한 순서에 의해서 작업들 호출 및 서브 클래스를 확장하는 방식). 템플릿 메서드 패턴은 상속을 통해 기능을 확장한 것
////    }
////}
//
//
////fun main(args: Array<String>) {
////    val applicationContext = GenericWebApplicationContext()
////    // 스프링 컨테이너는 어떤 클래스의 메타정보를 넣어주는 방식으로 빈을 생성한다.
////    // 스프링 컨테이너는 싱글톤 방식으로 동작하기에 싱글톤 레지스트리라고도 한다.
////    // 동일한 빈을 여러 서블릿 컨테이너에서 사용할 수가 있다.
////
////    applicationContext.apply {
////        registerBean(HelloService::class.java, Supplier { SimpleHelloService() })
////        registerBean(HelloController::class.java, Supplier { HelloController(applicationContext.getBean(HelloService::class.java)) })
////        refresh()
////    }
//////    applicationContext.registerBean(HelloService::class.java, Supplier { SimpleHelloService() }) // 인터페이스가 아닌 클래스 타입으로 제공해야 한다.
//////    applicationContext.registerBean(HelloController::class.java, Supplier { HelloController(applicationContext.getBean(HelloService::class.java)) })
//////    applicationContext.refresh() // 컨테이너 초기화
////
////    val serverFactory = TomcatServletWebServerFactory()
////    val webServer = serverFactory.getWebServer(ServletContextInitializer {
////        it.addServlet("dispatcherServlet", DispatcherServlet(applicationContext)).addMapping("/*")
////    })
////
////    webServer.start()
////}
//
////fun main(args: Array<String>) {
////    val applicationContext = GenericApplicationContext()
////    // 스프링 컨테이너는 어떤 클래스의 메타정보를 넣어주는 방식으로 빈을 생성한다.
////    // 스프링 컨테이너는 싱글톤 방식으로 동작하기에 싱글톤 레지스트리라고도 한다.
////    // 동일한 빈을 여러 서블릿 컨테이너에서 사용할 수가 있다.
////    applicationContext.registerBean(HelloService::class.java, Supplier { SimpleHelloService() }) // 인터페이스가 아닌 클래스 타입으로 제공해야 한다.
////    applicationContext.registerBean(HelloController::class.java, Supplier { HelloController(applicationContext.getBean(HelloService::class.java)) })
////    applicationContext.refresh() // 컨테이너 초기화
////
////    val serverFactory = TomcatServletWebServerFactory()
////    val webServer = serverFactory.getWebServer(ServletContextInitializer {
//////        val helloController = HelloController()
////
////        it.addServlet(
////            "frontcontroller",
////            object : HttpServlet() {
////                override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
////                    // 인증, 보안, 다국어, 공통 기능
////                    if (req.requestURI.equals("/hello") && req.method == HttpMethod.GET.name()) {
////                        val name = req.getParameter("name")
////
////                        val helloController = applicationContext.getBean(HelloController::class.java)
////                        val result = helloController.hello(name) // 바인딩이라고 한다. 웹 요청으로 받은 것을 처리하는 오브젝트에서 사용할 수 있도록 하는 것.
////
//////                        resp.status = HttpStatus.OK.value() // 서블릿에서 특별히 오류를 내지 않으면 200 OK가 리턴이 되긴한다. (Default 값임)
//////                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE) // 이와 같은 방법보다 아래의 방법이 더 좋음
////                        resp.contentType = MediaType.TEXT_PLAIN_VALUE
////                        resp.writer?.println(result)
////                    } else if (req.requestURI.equals("/user")) {
////                        resp.status = HttpStatus.ACCEPTED.value()
////                    } else {
////                        resp.status = HttpStatus.NOT_FOUND.value()
////                    }
////                }
////            }
////        ).addMapping("/*") // 모든 요청을 프론트 컨트롤러가 받는다. (중앙 집중화)
////    })
////
////    webServer.start()
////}
//
///**
// * 위의 결과는 다음과 같다.
// */
////~ http -v ":8080/hello?name=DolphaGo"
////GET /hello?name=DolphaGo HTTP/1.1
////Accept: */*
////Accept-Encoding: gzip, deflate
////Connection: keep-alive
////Host: localhost:8080
////User-Agent: HTTPie/3.2.1
////
////
////
////HTTP/1.1 200
////Connection: keep-alive
////Content-Length: 15
////Content-Type: text/plain;charset=ISO-8859-1
////Date: Mon, 23 Jan 2023 17:36:28 GMT
////Keep-Alive: timeout=60
////
////Hello DolphaGo
