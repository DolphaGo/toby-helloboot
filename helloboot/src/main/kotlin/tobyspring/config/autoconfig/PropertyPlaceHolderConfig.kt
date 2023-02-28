package tobyspring.config.autoconfig

import org.springframework.context.annotation.Bean
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import tobyspring.config.MyAutoConfiguration

/**
 * 사실상 @Value 는 프로퍼티 파일들과 그대로 연결해주는 것이 아니다.
 * 잘못 알고 있는 것들이, SpringBoot가 자동으로 해준다고 알고 있는 것인데, 사실 autoConfigure에 의해서 설정된 것이다.
 *
 * BeanFactoryPostProcessor 빈 후처리기로 동작하고 있기 때문에, 이 빈을 등록해주자.
 * 이 프로젝트에서는 autoConfiguration 으로 처리해준다.
 */
@MyAutoConfiguration
class PropertyPlaceHolderConfig {
    @Bean
    fun propertySourcesPlaceholderConfigurer(): PropertySourcesPlaceholderConfigurer {
        return PropertySourcesPlaceholderConfigurer()
    }
}
