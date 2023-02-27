package tobyspring.study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ConfigurationTest {

    @Test
    fun configuration() {
//        val common = Common()
//        Assertions.assertThat(common).isSameAs(common)
//
//        val myConfig = MyConfig()
//        val bean1 = myConfig.bean1()
//        val bean2 = myConfig.bean2()
//
//        Assertions.assertThat(bean1.common).isSameAs(bean2.common) // MyConfig를 스프링 구성정보로 활용하지 않으면 False다

        val ac = AnnotationConfigApplicationContext()
        ac.register(MyConfig::class.java)
        ac.refresh()

        val bean1 = ac.getBean(Bean1::class.java)
        val bean2 = ac.getBean(Bean2::class.java)

        /**
         * 만약 MyConfig에 @Configuration(proxyBeanMethods = false) 인 경우에는 프록시가 사용되지 않기 때문에, 다음 테스트는 실패한다.
         */
        Assertions.assertThat(bean1.common).isSameAs(bean2.common) // MyConfig를 스프링 구성정보로 활용하게 되니, True가 된다.
    }

    @Test
    fun proxyCommonMethod() {
        // given
        val myConfigProxy = MyConfigProxy()

        val bean1 = myConfigProxy.bean1()
        val bean2 = myConfigProxy.bean2()

        // then
        Assertions.assertThat(bean1.common).isSameAs(bean2.common) // 스프링 컨테이너의 도움을 받은 건 아니지만, 컨테이너의 흉내를 낸 것(캐싱). 이 결과는 True이다
    }
    class MyConfigProxy: MyConfig() {
        private var common: Common? = null
        override fun common(): Common {
            if(common == null){
                common = super.common()
            }

            return common!!
        }
    }

    @Configuration
    class MyConfig {
        @Bean
        fun common(): Common = Common()

        @Bean
        fun bean1(): Bean1 = Bean1(common())

        @Bean
        fun bean2(): Bean2 = Bean2(common())
    }

    class Bean1(val common: Common)
    class Bean2(val common: Common)
    class Common
    // Bean1 <-- Common
    // Bean2 <-- Common
}
