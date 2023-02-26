package tobyspring.hello

//class MySpringApplication
//
//fun run(applicationClass: Class<*>, args: Array<String>) {
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
